<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/admincommon/header.jsp"%>
<%@ page import="com.empty.member.model.vo.Member"%>
<%@ page import="java.util.List"%>
<%
	List<Member> list = (List) request.getAttribute("list");
	int cPage =request.getParameter("cPage")==null?1:Integer.parseInt(request.getParameter("cPage"));
	int numPerPage = request.getParameter("numPerPage")==null?10:Integer.parseInt(request.getParameter("numPerPage")); 
	String searchType = request.getParameter("searchType");
	String keyword = request.getParameter("searchKeyword");
	System.out.println(keyword);
%>




<section>

	<!-- 관리자 서브메뉴 -->
	<!-- <div id="adminSubMenu">
               <ul>
                   <li><a href="#"><span class="text-item">Connect Us</span></a></li>
                   <li><a href="#"><span class="text-item">회사소개</span></a></li>
                   <li><a href="#"><span class="text-item">자주묻는 질문</span></a></li>
                   <li><a href="#"><span class="text-item">Q&A</span></a></li>
                   <li><a href="#"><span class="text-item">1:1 문의</span></a></li>
               </ul>
                          
            </div> -->
	<div class="list_btn_area">

		<div class="searchDiv">
				<div class="selectSearchType">
					<select id="searchType" onchange="choiceType()">

						<option value="username" <%=searchType.equals("username")?"selected":""%>)>이름 검색</option>
						<option value="gender" <%=searchType.equals("gender")?"selected":"" %>>성별 검색</option>
						<option value="phone" <%=searchType.equals("phone")?"selected":"" %>>전화번호 검색</option>

					</select>
					
					<div class="searchBoxGra" id="search-username">
						
							<input class="searchBox" type="text" name="searchKeyword" placeholder="이름 검색하기" value="<%=searchType.equals("username")?keyword:""%>"> 
							<input type="hidden" name="searchType" value="username"/>
							<input type="button" value="검색" class="search-btn" onclick="searchAjax()">
						
					</div>
					<div class="searchBoxGra" id="search-gender">
						
							<select name="searchKeyword" class="searchBox" >
								<option  value="남" <%=searchType.equals("gender")&&keyword.equals("남")?"selected":""%>>남자</option>
								<option value="여" <%=searchType.equals("gender")&&keyword.equals("여")?"selected":""%>>여자</option>
							</select>
							<input type="hidden" name="searchType" value="gender"/>
							<input type="submit" value="검색" class="search-btn" onclick="searchAjax()">
						
					</div>
					<div class="searchBoxGra" id="search-phone">
						
							<input class="searchBox" type="text" name="searchKeyword"placeholder="전화번호 검색하기"  value="<%=searchType.equals("phone")?keyword:""%>"> 
							<input type="hidden" name="searchType" value="phone"/>
							<input type="submit" value="검색" class="search-btn" onclick="searchAjax()">
						
					</div>
				</div>

			</div>

	</div>
	<div id="primaryContent">
		<span id="totalSearch"></span>
		
				<form>
					<input type="hidden" name="cPage" value="<%=cPage%>" id="cPage">
					<select name="numPerPage" id="numPerPageSearch">
						<option value="10" <%=numPerPage==10?"selected":"" %>>목록 10개</option>
						<option value="20"<%=numPerPage==20?"selected":"" %>>목록 20개</option>
						<option value="30"<%=numPerPage==30?"selected":"" %>>목록 30개</option>
						<option value="50"<%=numPerPage==50?"selected":"" %>>목록 50개</option>
					</select>
				</form>
			
		<table class="contentTable" summary="">

			<thead>
				<tr>
					<th class="chk"><input id="allCheck" type="checkbox" value=""></th>
					<th class="userid_">아이디</th>
					<th class="username_">회원이름</th>
					<th class="userdiv_">회원 구별</th>
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
		<button></button>
	
	</div>




	<!-- content end -->
	


</section>


<script type="text/javascript" src="<%=request.getContextPath()%>/js/base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/adminPage/manageUser.js"></script>
<script>
           
        $(function(){
        	choiceType();//검색창 유지하기 
         	searchKeyType('<%=searchType%>','<%=keyword%>',1,10);
        	console.log("검색창유지,ajax보내기");
        })
		

		function searchKeyType (type,key,cPage,numPerPage){
			$.ajax({
				url:"<%=request.getContextPath()%>/admin/user/searchTypeAjax",
				dataType : "json",
				type : "get",
				data : {"searchType":type,"searchKeyword":key,"cPage" :cPage,"numPerPage" :numPerPage},
				success : function(data) {
						const attach = $("#tbody");
					if(data.length>1){
						
						for (let i = 0; i < data.length-2; i++) {
							const tr = $("<tr>");
							tr.append($("<td>").append($("<input>").attr({name:"dataid",type : "checkbox",class :"chkone",value : data[i]['userid']})));
							tr.append($("<td>").html(data[i]['userid']).addClass('userid_'));
							tr.append($("<td>").html(data[i]['username']).addClass('username_'));
							tr.append($("<td>").html(data[i]['userdiv']).addClass('userdiv_'));
							tr.append($("<td>").html(data[i]['gender']).addClass('gender_'));
							tr.append($("<td>").html(data[i]['phone']).addClass('phone_'));
							tr.append($("<td>").html(data[i]['email']).addClass('email_'));
							tr.append($("<td>").html(data[i]['address']).addClass('addr_'));
							tr.append($("<td>").html(data[i]['enrolldate']).addClass('enrolldate_'));
							i==0?attach.html(tr):attach.append(tr);

						}
						
						$("#totalSearch").html("검색결과 : "+data[data.length-2]['result']+ "개 입니다.");
						$(".paging").html(data[data.length-1]['pageBar']);
						//$("#numPerPage>option[value="+numPerPage+"]").prop("selected",true);안됨
						
					}else{
						attach.html("");
						$(".paging").html(data[0]['msg']);
					}
				
					
					
				},
				error : function(request, status, error) {
						//console.log(request, status, error);
						if (request.status == 404)
							//$("#content").append(request.status);
							$(".paging").html("페이지를 찾을 수 없습니다.");
				}
				

			})
		}
        $(function(){
    		
			$("#userPage>a").css("color","#ff7531");
	})
	</script>

</body>

</html>