<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.empty.member.model.vo.Member"%>
<%
	Member m = (Member) request.getAttribute("Member");
	int cPage =request.getParameter("cPage")==null?1:Integer.parseInt(request.getParameter("cPage"));
	int numPerPage = request.getParameter("numPerPage")==null?5:Integer.parseInt(request.getParameter("numPerPage"));
%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/mypage/mypageMain.css?ver=7"
	type="text/css">
<%@ include file="/views/common/header.jsp"%>

<section id="mypageMainSection">
	<div id="mypageMainDiv" class="col-8">
		<h1>MY PAGE</h1>
		<div class="mypageHeaderDiv">
			<span class="mypageHeader">개인정보</span> <input id="infoReviseBtn"
				class="mypageHeaderBtn" type="button" value="수정하기"
				onclick="infoRevise()">
		</div>
		<table id="mypageMainTable">
			<tr>
				<th>아이디</th>
				<td><input type="hidden" name="userId"> <%=loginMember.getUserId()%></td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>***********</td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=loginMember.getUserName()%></td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=loginMember.getEmail()%></td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><%=loginMember.getPhone()%></td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><%=loginMember.getAddress()%></td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=loginMember.getGender()%></td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td><%=loginMember.getEnrollDate()%></td>
				<td style="width: 50px"></td>
			</tr>
			<tr>
				<th>빈캐시</th>
				<td><%=loginMember.getCash() %></td>
				<td style="width: 50px"><input type="button" value="충전하기" onclick="chargeBtn()"></td>
			</tr>
		</table>
	</div>
	<div id="mypageUseDiv">
		<div class="mypageHeaderDiv">
			<span class="mypageHeader">사용내역</span>
		</div>
		<div id="mypageUseTable">
			
		</div>
	</div>
		<div class="pageBar">
		
		</div>

	<div id="mypagePayDiv">
		<div class="mypageHeaderDiv">
			<span class="mypageHeader">결제내역</span>
		</div>
		<div id="mypagePayTable">

		</div>
	</div>
	<div class="pageBar2">
	
	</div>
</section>
<script>

//사용내역 페이징
$(function (){
	requestData(1,5);
})
	

function requestData(cPage,numPerPage){
/* $(function(){
      $("#mypageUseDiv").click(function(){
    	  
    	  if($("#mypageUseTable").html() == "") { */
    		  $.ajax({
    	            url:"<%=request.getContextPath()%>/mypageUse",
    	            type:"get",
    	            dataType:"json",
    	            data:{"cPage":cPage,"numPerPage":numPerPage,"userId":"<%=loginMember.getUserId()%>"},
    	            success:function(data){
    	            	$("#mypageUseTable").html("");
    	            	console.log("페이징처리");
    	            	if(data.length>1){
    	            		const table=$("<table>");
    	            		const th=$("<tr>");
    	            		th.append($("<th>날짜</th>")).append($("<th>매장명</th>")).append($("<th>사용금액</th>"));
    	            		table.append(th);
    	            		console.log(data.length);
    	             	  for(let i=0;i<data.length-1;i++){	
    	             		  console.log(i);
    	            		   let tr=$("<tr>");
    	            		   let td=$("<td>").html(data[i]['sTime']);
    	            	 	   tr.append(td);
    	            		   td=$("<td>").html(data[i]['storeName']);
    	            		   tr.append(td);
    	            		   td=$("<td>").html(data[i]['payMoney']);
    	            		   tr.append(td);
    	            		   table.append(tr);
    	            		   i==0?table.html(tr):table.append(tr);
    	               		}
    	            	   $("#mypageUseTable").append(table);
    	             	  $(".pageBar").html(data[data.length-1]['pageBar']);
    	            	}else{
    	            		$(".pageBar").html(data[0]['msg']);
    	            	}
    	            },error:(r,e,m)=>{
                        console.log(r);
                        console.log(e);
                        console.log(m);
                     }
    	         })
    	  /* } else {
    		  $("#mypageUseTable").html("");
    		  $(".pageBar").html("");
    	  }
         
      });        
   }); */
};



//결제내역 페이징
$(function (){
	requestData2(1,5);
})
function requestData2(cPage2,numPerPage2){
	    		  $.ajax({
	    	            url:"<%=request.getContextPath()%>/mypagePay",
	    	            type:"get",
	    	            dataType:"json",
	    	            data:{"cPage2":cPage2,"numPerPage2":numPerPage2,"userId2":"<%=loginMember.getUserId()%>"},
	    	            success:function(data){
	    	            	console.log("paycharge");
	    	            	$("#mypagePayTable").html("");
	    	            	if(data.length>1){
	    	            		const table=$("<table>");
	    	            		const th=$("<tr>");
	    	            		th.append($("<th>날짜</th>")).append($("<th>결제금액</th>"));
	    	            		table.append(th);
	    	            		console.log(data.length);
	    	             	  for(let i=0;i<data.length-1;i++){	
	    	             		  console.log(i);
	    	            		   let tr=$("<tr>");
	    	            		   let td=$("<td>").html(data[i]['ipDate']);
	    	            	 	   tr.append(td);
	    	            		   td=$("<td>").html(data[i]['ipMoney']);
	    	            		   tr.append(td);
	    	            		   table.append(tr);
	    	            		   i==0?table.html(tr):table.append(tr);
	    	               		}
	    	            	   $("#mypagePayTable").append(table);
	    	             	  $(".pageBar2").html(data[data.length-1]['pageBar2']);
	    	            	}else{
	    	            		$(".pageBar2").html(data[0]['msg2']);
	    	            	}
	    	            },error:(r,e,m)=>{
	                        console.log(r);
	                        console.log(e);
	                        console.log(m);
	                     }
	    	         })
	};

   
<%--    $(function(){
      $("#mypagePayDiv").click(function(){
    	  if($("#mypagePayTable").html() == ""){
         $.ajax({
            url:"<%=request.getContextPath()%>/mypagePay",
            type:"get",
            dataType:"html",
            success:function(data){
               $("#mypagePayTable").html(data); 
            }
         })
      }else{
    	  $("#mypagePayTable").html("");
      }
      });         
   }); --%>
   
   $(function(){
	   $("#infoReviseBtn").click(function(){
		   $.ajax({
			   url:"<%=request.getContextPath()%>/myInfoUpdate",
			   type:"post",
			   dataType:"html",
			   success:function(data){
				   console.log(data);
				   $("#mypageMainDiv").html("");
				   $("#mypageMainDiv").html(data);
				   $("#mypageUseTable").html("");
				   $(".pageBar").html("");
				   $("#mypagePayTable").html("");
				   $(".pageBar2").html("");
			   }
		   })
	   })
   })
   
   function infoUpdateBackBtn(){
	   location.replace("<%=request.getContextPath()%>/mypageMain");
   }
   
   function deleteMember(){
		location.replace("<%=request.getContextPath()%>/deleteMember");
	}
   
   function chargeBtn(){
	   location.replace("<%=request.getContextPath()%>/views/cash/mycash");
   }
   
   
</script>