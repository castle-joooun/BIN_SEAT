<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.empty.member.model.vo.Member,com.empty.common.listener.SessionCheckListener"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
	String userId = (String) session.getAttribute("uname");
	Cookie[] cookies = request.getCookies();
	String msg = null;
	String saveId = null;
	if (cookies != null) {
		for (Cookie c : cookies) {
			String key = c.getName();
			String value = c.getValue();
			if (key.equals("saveId")) {
				saveId = value;
			}else if(key.equals("loginFail")){
				msg = URLDecoder.decode(c.getValue(), "UTF-8");
			}
		}
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>빈시트 : pc방 자리찾기</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base2.css?ver=3.9" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/choiceSignUp.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/signUp_terms.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base2.css?ver=2.0" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/choiceSignUp.css" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/signUp_terms.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Jim+Nightshade&display=swap">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminPage/admin.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/adminPage/store.css">
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/base.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=57f292cf81a06c030ca86c61e79b1b56&libraries=services"></script>
<script>
        onchange
</script>
</head>

<body>
	<header style="height: 123px;">
		<div>
			<a href="<%=request.getContextPath()%>/home">
				<%-- <!--로고 변하게 하기 -->
				<img id="mainLogo" class="hover" src="<%=request.getContextPath()%>/image/빈시트500-2.png" width="150px"> --%>
				<!-- 원래 로고 -->
				<img id="transLogo" src="<%=request.getContextPath()%>/image/빈시트500-1.png" width="150px">
			</a>
		</div>
		<center>
			<!-- 상단고정 메뉴바 -->
			
				<span id="menubar">
                <!-- main페이지이면 main의 mainHover을 빼준다! ------------------------------------------------------------------->
                <p id="main" class="mainHover"><a href="<%=request.getContextPath() %>/" style="color: #ff7531">MAIN</a></p>
                <p id="userPage" class="mainHover"><a href="<%=request.getContextPath()%>/admin/manageUser">USER</a></p>
                <p id="storePage" class="mainHover"><a href="<%=request.getContextPath()%>/admin/manageStore">STORE</a></p>
                </span>
		</center>
		
		<!-- ----------로그인---------- -->
		
			
			<%
				if(loginMember != null){ 
			%>	
				<img onclick="location.replace('<%=request.getContextPath()%>/logout')" class="logoutBtn" src="<%=request.getContextPath()%>/image/logout.png"
					width="30px" style="position: absolute; float: right; left: 1130px; cursor: pointer; top: 33px; z-index:3;">
				<%-- <button type="button" class="logoutBtn" onclick="location.replace('<%=request.getContextPath()%>/logout')">로그아웃</button> --%>
			<%
				}else{
			%>
				<img onclick="document.getElementById('openLogin').style.display='block'" id="popMenu" src="<%=request.getContextPath()%>/image/login.png"
					width="30px">
			<%
				}
			%>

	</header>
	<%if(loginMember == null && msg == null) {%>
	<div id="openLogin" class="modal">
		<center>
			<div class="modal-content" id="login">
			    <span onclick="document.getElementById('openLogin').style.display='none'" class="close" title="Close Modal">&times;</span>
				<h1>로그인</h1>
				<hr>
				<!-- 아이디 / 비번 입력창  -->
				<form id="loginData" action="<%=request.getContextPath()%>/login" method="post">
					<div id="inputData" class="container">
						<b>아이디</b><input class="input" type="text" placeholder="아이디" name="uname" id="uId" value="<%=saveId != null ? saveId : ""%>">
						<b>비밀번호</b><input class="input" type="password" onkeyup="enterkey();" placeholder="비밀번호" name="psw" id="pw">
					</div>
					<div class="checkBox">
						<input type="checkbox" name="saveId" id="saveId" <%=saveId != null ? "Checked" : ""%>>
						<label for="saveId">아이디저장&nbsp;&nbsp;</label><br>
					</div>
				</form>
			<!-- 로그인 / 취소 버튼 -->
			<button type="button" class="buttonStyle" id="loginBtn" onclick="checkMember()">로그인</button>
			<button type="button" class="buttonStyle" onclick="document.getElementById('openLogin').style.display='none'">취소</button>
			<!-- 회원가입 / 계정찾기 링크 -->
			<div class="container">
				<ul>
					<li style="list-style: none;">
						<a class="link" href="<%=request.getContextPath()%>/signUpChoice">회원가입</a>
					</li>
					<br>
					<li style="list-style: none;">
						<a class="link" href="<%=request.getContextPath()%>/findIdView">아이디 찾기&nbsp;</a>
						<a class="link" href="<%=request.getContextPath()%>/findPwView">&nbsp;비밀번호 찾기</a></li>
					</div>
				</ul>
			</div>
		</center>
	</div>
	<%}else if(loginMember == null && msg != null) {%>
		<div id="openLogin" class="modal">
		<center>
			<div class="modal-content" id="login">
			    <span onclick="document.getElementById('openLogin').style.display='none'" class="close" title="Close Modal">&times;</span>
				<h1>로그인</h1>
				<hr>
				<!-- 아이디 / 비번 입력창  -->
				<form id="loginData" action="<%=request.getContextPath()%>/login" method="post">
					<div id="inputData" class="container">
						<b>아이디</b><input class="input" type="text" placeholder="아이디" name="uname" id="uId" value="<%=saveId != null ? saveId : ""%>">
						<b>비밀번호</b><input class="input" type="password" onkeyup="enterkey();" placeholder="비밀번호" name="psw" id="pw">
					</div>
					<div class="checkBox">
						<input type="checkbox" name="saveId" id="saveId" <%=saveId != null ? "Checked" : ""%>>
						<label for="saveId">아이디저장&nbsp;&nbsp;</label><span style="color:red"><%=msg %></span><br>
					</div>
				</form>
			<!-- 로그인 / 취소 버튼 -->
			<button type="button" class="buttonStyle" id="loginBtn" onclick="checkMember()">로그인</button>
			<button type="button" class="buttonStyle" onclick="document.getElementById('openLogin').style.display='none'">취소</button>
			<!-- 회원가입 / 계정찾기 링크 -->
			<div class="container">
				<ul>
					<li style="list-style: none;">
						<a class="link" href="<%=request.getContextPath()%>/signUpChoice">회원가입</a>
					</li>
					<br>
					<li style="list-style: none;">
						<a class="link" href="<%=request.getContextPath()%>/findIdView">아이디 찾기&nbsp;</a>
						<a class="link" href="<%=request.getContextPath()%>/findPwView">&nbsp;비밀번호 찾기</a></li>
					</div>
				</ul>
			</div>
		</center>
	</div>
	<script>
		$(function(){
			var v = document.getElementById('openLogin');
			v.style.display = "block";
		})

     

    </script>
	</script>
	<%
		Cookie ck = new Cookie("loginFail", null);
		ck.setMaxAge(0);
		response.addCookie(ck);
	%>
	<%} %>


	<!-- 미들 바 -->
	<nav id="middle">
		<p id="middleText">세상의 모든 자리 찾기</p>




