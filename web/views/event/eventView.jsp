<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.empty.event.model.vo.Event, com.empty.search.model.vo.Store"%>
<%
	Event e = (Event) request.getAttribute("Event");
	Store s = (Store) request.getAttribute("store");
%>

<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/event/eventView.css" type="text/css">
<center>
<section>
	<div id="eventViewDiv">
		<p id="eventViewHead">이벤트</p>
		<table class="eventViewTbl">
			<tr>
				<th>번 호</th>
				<th>제 목</th>
				<th>PC방 ▼</th>
				<th>작성일</th>
				<th>조회 수</th>
			</tr>
			<tr>
				<td><%=e.getEventNo()%></td>
				<td id="eventTitleTd"><%=e.getEventTitle()%></td>
				<td>
					<form id="storeMoving" action="<%=request.getContextPath() %>/storeView" method="post">
							<input type="hidden" name="storeId" value="<%=s.getStoreId()%>">
							<input type="hidden" name="userId" value="<%=loginMember!=null?loginMember.getUserId():""%>">
							<input type="hidden" name="searchText" value="<%=s.getStoreName()%>">
							<span id="storeMove"><%=e.getEventWriter()%></span>
					</form>
				</td>
				<td><%=e.getEventDate()%></td>
				<td><%=e.getEventCount()%></td>
			</tr>
			<tr>
			</tr>
			<tr class="eventContent">
				<td colspan="5"  style="white-space: pre-wrap;"><%=e.getEventContent()%></td>
			</tr>
		</table>
	</div>
	<%if(loginMember != null && loginMember.getUserId().equals(e.getEventWriter())){ %>
	<div>
		<button id="eventUpdateBtn" onclick="eventUpdate()">수 정</button>
		<button id="eventDeleteBtn" onclick="eventDelete()">삭 제</button>
	</div>
	<%} %>
	<div style="margin-top: 30px;">
		<button class="returnBtn" onclick="location.replace('<%=request.getContextPath()%>/event')">목록으로</button>
	</div>
	
</section>
</center>
<script>
	function eventUpdate(){
		location.replace("<%=request.getContextPath()%>/event/eventUpdate?no=<%=e.getEventNo()%>");
	}
	
	function eventDelete(){
		if(confirm("게시물을 삭제하시겠습니까?")){
			location.replace("<%=request.getContextPath()%>/event/eventDelete?no=<%=e.getEventNo()%>");
		}else{
			window.close();
		} 
	}
	
	$("#storeMove").click(function() {
		$("#storeMoving").submit();
	})
</script>