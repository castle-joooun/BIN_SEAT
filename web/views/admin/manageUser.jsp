<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/admincommon/header.jsp"%>
<%@ page import="com.empty.member.model.vo.Member"%>
<%@ page import="java.util.List"%>
<%
	List<Member> list = (List) request.getAttribute("list");
	int cPage =request.getParameter("cPage")==null?1:Integer.parseInt(request.getParameter("cPage"));
	int numPerPage = request.getParameter("numPerPage")==null?10:Integer.parseInt(request.getParameter("numPerPage")); 
	
%>




<section>


	<div class="list_btn_area">
		<div class="searchDiv">
				<div class="selectSearchType">
					<select id="searchType" onchange="choiceType()">

						<option value="username">이름 검색</option>
						<option value="gender">성별 검색</option>
						<option value="phone">전화번호 검색</option>

					</select>
					
					<div class="searchBoxGra" id="search-username">
						<form action ="<%=request.getContextPath()%>/admin/searchType" method="post">
							<input type="hidden" name="searchType" value="username"/>
							<input class="searchBox" type="text" name="searchKeyword" placeholder="이름 검색하기" > 
							<input type="button" value="검색" class="search-btn" onclick="doSubmit();">
						</form>
					</div>
					<div class="searchBoxGra" id="search-gender">
						<form action ="<%=request.getContextPath()%>/admin/searchType" method="post">
							<select name="searchKeyword" class="searchBox" >
								<option value="남">남자</option>
								<option value="여">여자</option>
							</select>
							<input type="hidden" name="searchType" value="gender"/>
							<input type="button" value="검색" class="search-btn" onclick="doSubmit();">
						</form>
					</div>
					<div class="searchBoxGra" id="search-phone">
						<form action ="<%=request.getContextPath()%>/admin/searchType" method="post">
							<input class="searchBox" type="text" name="searchKeyword"placeholder="전화번호 검색하기" > 
							<input type="hidden" name="searchType" value="phone"/>
							<input type="button" value="검색" class="search-btn" onclick="doSubmit();">
						</form>
					</div>
				</div>
		</div>

	</div>
	<div id="primaryContent">
		<span id="totalSearch"></span>
		
				<form>
					<input type="hidden" name="cPage" value="<%=cPage%>" id="cPage">
					<select name="numPerPage" id="numPerPage">
						<option value="10" <%=numPerPage==10?"selected":"" %>>목록 10개</option>
						<option value="20"<%=numPerPage==20?"selected":"" %>>목록 20개</option>
						<option value="30"<%=numPerPage==30?"selected":"" %>>목록 30개</option>
						<option value="50"<%=numPerPage==50?"selected":"" %>>목록 50개</option>
					</select>
				</form>
			
		<table class="contentTable" summary="">

			<thead>
				<tr>
					<!-- <th class="chk"><input id="allCheck" type="checkbox" value=""></th> -->
					<th class="userid_">아이디</th>
					<th class="username_">회원이름</th>
					<!-- <th class="userdiv_">회원 구별</th> -->
					<th class="gender_">성별</th>
					<th class="phone_">전화번호</th>
					<th class="email_" nowrap="nowrap">이메일</th>
					<th class="addr_" nowrap="nowrap">주소</th>
					<th class="enrolldate_" nowrap="nowrap">가입일자</th>

				</tr>
			</thead>
			<tbody id="tbody">

				<%--<%for(Member m : list){ %>
                            <tr class="myarticle">
                            	<th class="chk"><input id="allCheck" type="checkbox" value=""></th>
                                <th class="userid_"><%=m.getUserId()%></th>
                                <th class="userdiv_"><%=m.isUserDiv()%></th>
                                <th class="username_"><%=m.getusername()%></th>
                                <th class="gender_"><%=m.getGender()%></th>
                                <th class="phone_"><%=m.getPhone()%></th>
                                <th class="email_" ><%=m.getEmail()%></th>
                                <th class="addr_" ><%=m.getAddress()%></th>
                                <th class="enrolldate_" ><%=m.getEnrollDate()%></th>
                                
							</tr>
                            <%} %>     --%>
			</tbody>
		</table>
		<!-- end bbsList -->

	</div>
	<!-- paging -->
	<div class="list_btn_area">
		<div class="paging">
			
		</div>
		
	
	</div>




	<!-- content end -->
	


</section>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/adminPage/manageUser.js"></script>
<script>
		//관리자-유저페이지 들어오면실행되는것
		$(function(){
			requestData(1,10);
		})
		function requestData(cPage,numPerPage){
			console.log("기본 페이징처리");
            	$.ajax({
            	url:"<%=request.getContextPath()%>/admin/user/ajaxPaging",
				dataType : "json",
				type : "get",
				data : {
					"cPage" :cPage,"numPerPage" :numPerPage},
				success : function(data) {
					console.log("성공했을때");
					if(data.length>1){
						const attach = $("#tbody");
						console.log(data);
						for (let i = 0; i < data.length-2; i++) {
							const tr = $("<tr>");
							/* tr.append($("<td>").append($("<input>").attr({name:"dataid",type : "checkbox",class :"chkone",value : data[i]['userid']}))); */
							tr.append($("<td>").html(data[i]['userid']).addClass('userid_'));
							tr.append($("<td>").html(data[i]['username']).addClass('username_'));
							/* tr.append($("<td>").html(data[i]['userdiv']).addClass('userdiv_')); */
							tr.append($("<td>").html(data[i]['gender']).addClass('gender_'));
							tr.append($("<td>").html(data[i]['phone']).addClass('phone_'));
							tr.append($("<td>").html(data[i]['email']).addClass('email_'));
							tr.append($("<td>").html(data[i]['address']).addClass('addr_'));
							tr.append($("<td>").html(data[i]['enrolldate']).addClass('enrolldate_'));
							i==0?attach.html(tr):attach.append(tr);

						}
						$("#totalSearch").html("검색결과 : "+data[data.length-2]['result']+ "개 입니다.");
						$(".paging").html(data[data.length-1]['pageBar']);
						
					}else{
						$(".paging").html(data[0]['msg']);
					}
					
					
				},
				error : function(request, status, error) {
					//console.log(request, status, error);
					if (request.status == 404)
						//$("#content").append(request.status);
						console.log("페이지를 찾을 수 없습니다.");
						$(".paging").html("페이지를 찾을 수 없습니다.");
				}

			})

            }
	
		
	</script>

</body>

</html>