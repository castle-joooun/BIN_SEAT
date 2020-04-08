<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.empty.member.model.vo.Member" %>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");

%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage/myInfoUpdate.css" type="text/css">

<h1>MY PAGE</h1>
	<div class="mypageHeaderDiv">
		<span class="mypageHeader">개인정보</span>
		
	</div>
	<form action="<%=request.getContextPath() %>/mypageUpdateEnd" method="post">
		<table id="mypageMainTable">
			<tr>
				<th>아이디</th>
				<td><input type="hidden" name="userId" value="<%=loginMember.getUserId() %>"><%=loginMember.getUserId() %></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="password" size="40"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" value="<%=loginMember.getUserName() %>" size="40"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" value="<%=loginMember.getEmail() %>" size="40"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" value="<%= loginMember.getPhone()%>" size="40"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr" value="<%=loginMember.getAddress() %>" size="40"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><%=loginMember.getGender() %></td>
			</tr>
			<tr>
				<th>가입일</th>
				<td><%=loginMember.getEnrollDate() %></td>
			</tr>
			<tr>
				<th>회원탈퇴</th>
				<td><input type="button" value="회원탈퇴" onclick="deleteMember()"></td>
			</tr>
		</table>
		<input type="submit" id="myInfoSubmitBtn" value="수정완료">
		<input type="button" value="취소" onclick="infoUpdateBackBtn()">
	</form>
	
