<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/admincommon/header.jsp"%>

<link rel="stylesheet" href="<%=request.getContextPath() %>/css/adminPage/store.css" type="text/css">
<section>


	<div class="list_btn_area">

		<div id="storeSubMenu">
			<ul>
				<li><a href="<%=request.getContextPath()%>/admin/manageStore"><span class="text-item">스토어 이용현황</span></a></li>
				<li><a href="<%=request.getContextPath()%>/admin/store/requestStoreList"><span class="">스토어 신청현황</span></a></li>
				
			</ul>
		</div>
		
		
	</div>

	</div>
	<div id="primaryContent">
		<div>
			<span id="totalSearch"></span>
			<form>
				<input type="hidden" name="cPage" value="" id="cPage"> 
				<select name="numPerPage" id="numPerPage">
					<option value="10">목록 10개</option>
					<option value="20">목록 20개</option>
					<option value="30">목록 30개</option>
					<option value="50">목록 50개</option>
				</select>
			</form>
		</div>
		<table class="contentTable" summary="">

			<thead>
				<tr>
					<!-- <th class="chk"><input id="allCheck" type="checkbox" value=""></th> -->
					<th class="userid_">스토어 아이디</th>
					<th class="username_">스토어 명</th>
					<th class="phone_">전화번호</th>
					<!-- <th class="email_" nowrap="nowrap">이메일</th> -->
					<th class="addr_" nowrap="nowrap">주소</th>
					<th class="enrolldate_" nowrap="nowrap">신청 일자</th>
					<th class="" nowrap="nowrap">관리</th>
				</tr>
			</thead>
			<tbody id="tbody">

			</tbody>
		</table>
		<!-- end bbsList -->

	</div>
	<!-- paging -->
	<div class="list_btn_area">
		<div class="paging"></div>
		

	</div>
</section>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/adminPage/store.js"></script>
<script>
$(function(){
	runningStore(1,10);
})
function runningStore(cPage,numPerPage){
	$.ajax({
		url:"<%=request.getContextPath()%>/admin/store/runningStoreAjax",
		dataType:"json",
		type:"post",
		data:{"cPage":cPage,"numPerPage":numPerPage},
		success:function(data){
			console.log(data);
			console.log(data[data.length-1]);
			console.log(data[data.length-2]);
			if(data.length>1){
				const tbody = $("#tbody");
				
				for(let i = 0;i<data.length-2;i++){
					const tr =$("<tr>");
				 	/* tr.append($("<td>").append($("<input>").attr({name:"dataid",type:"checkbox",class:"chkone",value:data[i]['storeId'],onclick:""}))); */	
					tr.append($("<td>").html(data[i]['storeId']).addClass("userid_"));
					tr.append($("<td>").html(data[i]['storeName']).addClass("username_"));
					tr.append($("<td>").html(data[i]['storePhone']).addClass("phone_"));
					/* tr.append($("<td>").html(data[i]['email']).addClass("email_")); */
					tr.append($("<td>").html(data[i]['storeAddress']).addClass("address_"));
					tr.append($("<td>").html(data[i]['enrollDate']).addClass("enrolldate_"));
					tr.append($("<td>").append($("<button>").addClass("apprBtn_").html("스토어 삭제").attr({type:"button",onclick:"deleteStore();",value:data[i]['storeId']})));
					i ==0?tbody.html(tr):tbody.append(tr);
					
				}
				$(".paging").html(data[data.length-1]);//페이지바
				$("#totalSearch").html("검색 결과: "+data[data.length-2]+ "개 입니다.");//총검색결과
				
			  
			}else{
				$(".paging").html(data[0]);
			}
		
			
		},
		error: function(request,status,error){
			if (request.status == 404){
				
				console.log("페이지를 찾을 수 없습니다.");
				$(".paging").html("페이지를 찾을 수 없습니다.");
			}
		}
	})
}


</script>


