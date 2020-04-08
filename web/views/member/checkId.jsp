<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	boolean isUseable = (boolean)request.getAttribute("isUseable");
	String userId = (String)request.getParameter("userId");
	String uId = (String)request.getAttribute("userId");
%> 
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인</title>
</head>
<body>
	<div id="checkid-container">
		<%if(isUseable){ %>
			[<span><%=uId %></span>]는 사용이 가능합니다.
			<br><br>
			<button	type='button' onclick="setUserId();">닫기</button>
		<%} else{%>
			[<span><%=uId %></span>]는 이미 사용 중 입니다.
			<br><br>
			<input type="text" id="userId"  placeholder="아이디를 입력하세요" />&nbsp;&nbsp;<button type="button"  onclick="checkIdDuplicate();">중복검사</button>
			<span class="span" id="idCondition"></span>
		<%} %>
	</div>
	<script>
		function checkIdDuplicate(){
			//재검색을 위한 로직구성
			const userId = document.getElementById("userId").value;
			//checkIdDuplcate로 다시 작성한 userId를 전송하는 로직 구성
			location.href="<%=request.getContextPath()%>/checkIdDuplicate?userId="+userId;
		}
		
		function setUserId(){
			//검색한 userId를 부모창의 userId_에 값을 넣기
			opener.document.getElementById("userId").value='<%=userId %>';
			//넣은 후 창 닫기
			self.close();
		}
	</script>
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkId.js"></script>
</body>
</html>






