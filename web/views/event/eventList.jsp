<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,com.empty.event.model.vo.Event"%>
<%
	List<Event> list = (List) request.getAttribute("list");
	boolean flag = (boolean)request.getAttribute("flag");
%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/event/eventList.css" type="text/css">
<center>
<section>
	<div id="eventListDiv">
	   	<div style="text-align: left; margin-left: 140px; margin-bottom: 40px;">
	      <a href="<%=request.getContextPath()%>/notice" id="noticeBoard" class="noticeHead">공지사항&nbsp;&nbsp;&nbsp;&nbsp;</a>
	      <p style="display: inline" class="headDiv">|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
	      <a href="<%=request.getContextPath()%>/event" id="eventBoard"  class="noticeHead" style="color: tomato; border-bottom: 3px solid #5e64b1;" >이벤트</a>
	    </div>
		<%
			if(loginMember != null && flag){
		%>
		<div>
			<button type="button" id="eventWriteBtn" onclick="location.replace('<%=request.getContextPath()%>/eventWrite')">이벤트 작성</button>
		</div>
		<%}else{ %>
		<div style="height: 40px;">
		</div>
		<%} %>
		<table class="eventListTbl">
			<thead>
				<tr>
					<th>번 호</th>
					<th id="eventTitleTh">제 목</th>
					<th>PC방</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr> 
			</thead>
			<tbody>
				<%
					if(list.isEmpty()){
				%>
				<tr>
					<td>등록된 내용이 없습니다.</td>
				</tr>
				<%
					}else{
						for(Event e : list){
				%>
				<tr>
					<td style="color: blue"><%=e.getEventNo() %></td>
					<td>
						<span class="innerContent" onclick="link(<%=e.getEventNo()%>)">
							<%=e.getEventTitle() %>
							<form action="<%=request.getContextPath()%>/event/eventView">
								<input type="hidden" name="no" value="<%=e.getEventNo() %>">
							</form>
						</span>
					</td>
					<td><%=e.getEventWriter()%></td>
					<td><%=e.getEventDate() %></td>
					<td><%=e.getEventCount() %></td>
				</tr>
				<%
						}
					}
				%>
			</tbody>
		</table>
			<form action="<%=request.getContextPath()%>/event/eventView" id="linkF">
				<input type="hidden" name="no">
			</form>
		<center>
			<div id="pageBar">
				<%=request.getAttribute("pageBar")%>
			</div>
		</center>
	</div>
</section>
</center>
		<script>
		function link(no){
			$("#linkF>input").val(no);
			$("#linkF").submit();
		}
	</script>
</body>
</html>