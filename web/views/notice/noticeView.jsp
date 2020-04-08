<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.empty.notice.model.vo.Notice"%>
<%
	Notice n = (Notice) request.getAttribute("Notice");
%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/notice/noticeView.css" type="text/css">

<center>
<section>
	<div id="noticeViewDiv">
		<p id="noticeViewHead">공지사항</p>
		<table class="noticeViewTbl">
			<tr>
				<td>제 목</td>
				<td id="noticeTitleTd"><%=n.getNoticeTitle()%></td>
				<td>작성일</td>
				<td><%=n.getNoticeDate()%></td>
				<td>조회수</td>
				<td><%=n.getNoticeCount()%></td>
			</tr>
			<tr class="noticeContent">
				<td colspan="6" style="white-space: pre-wrap;"><%=n.getNoticeContent()%></td>
			</tr>
		</table>
	</div>
	<%if(loginMember != null && loginMember.getUserId().equals("admin")){ %>
	<div>
		<button id="noticeUpdateBtn" onclick="noticeUpdate()">수 정</button>
		<button id="noticeDeleteBtn" onclick="noticeDelete()">삭 제</button>
	</div>
	<%} %>
	<div style="margin-top: 30px;">
		<button class="returnBtn" onclick="location.replace('<%=request.getContextPath()%>/notice')">목록으로</button>
	</div>

</section>
</center>
<script>
	function noticeUpdate(){
		location.replace("<%=request.getContextPath()%>/notice/noticeUpdate?no=<%=n.getNoticeNo()%>");
	}
	
	function noticeDelete(){
		if(confirm("게시물을 삭제하시겠습니까?")){
			location.replace("<%=request.getContextPath()%>/notice/noticeDelete?no=<%=n.getNoticeNo()%>");
		}else{
			window.close();
		}
	}
</script>