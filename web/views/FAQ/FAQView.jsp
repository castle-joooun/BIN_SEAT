<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.empty.FAQ.model.vo.FAQ"%>
<%
	FAQ f = (FAQ) request.getAttribute("FAQ");
%>


<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/FAQ/FAQview.css?ver=3" type="text/css">
<center>
<section>
	<div id="FAQViewDiv">
		<p id="FAQViewHead">FAQ</p>
		<table id="FAQViewTbl">
			<tr>
				<th>글번호</th>
				<th>제 목</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<tr>
				<td><%=f.getNo()%></td>
				<td id="FAQTitleTd"><%=f.getTitle()%></td>
				<td><%=f.getTime()%></td>
				<td><%=f.getCount()%></td>
			</tr>
			<tr>
			</tr>
			<tr class="FAQContent">
				<td colspan="4"  style="white-space: pre-wrap;"><%=f.getContent()%></td>
			</tr>
		</table>
	</div>
	<%if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
	<div id="FAQviewBtn">
		<button id="FAQupdate" onclick="FAQupdate()">수정하기</button>
		<button id="FAQdelete" onclick="FAQdelete()">삭제하기</button>
	</div>
	<%} %>
	<div style="margin-top: 30px;">
		<button class="returnBtn" onclick="location.replace('<%=request.getContextPath()%>/FAQMainServlet')">목록으로</button>
	</div>

</section>
</center>
<script>
	function FAQupdate(){
		location.replace("<%=request.getContextPath()%>/FAQ/FAQupdate?no=<%=f.getNo()%>");
	}
	
	function FAQdelete(){
		if(confirm("게시물을 삭제하시겠습니까?")){
			location.replace("<%=request.getContextPath()%>/FAQ/FAQdelete?no=<%=f.getNo()%>");
		}else{
			window.close();
		}
	}
</script>