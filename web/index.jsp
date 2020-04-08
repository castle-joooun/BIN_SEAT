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
				System.out.println(msg);
			}
		}
	}
	
	int slideNum = 0;
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>빈시트-pc방 자리찾기</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css?ver=1" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/login.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/choiceSignUp.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/signUp_terms.css">
    <link href="https://fonts.googleapis.com/css?family=Jim+Nightshade&display=swap" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/js/base.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>
    <script>
        onchange
    </script>
</head>

<body>
    <header>
        <div>
            <a href="<%=request.getContextPath()%>">

                <!--로고 변하게 하기 -->
                <img id="mainLogo" src="image/로고-test.png" width="150px"> <!-- 원래 로고 -->
                <!-- <img id="transLogo" src="image/빈시트500-1-test.png" width="150px"> -->
            </a>
        </div>
        <nav>
            <center>
                <!-- 상단고정 메뉴바 -->
                <div id="menubar">
                    <!-- main페이지이면 main의 mainHover을 빼준다! ------------------------------------------------------------------->
                    <p id="main"><a href="<%=request.getContextPath() %>" style="color: white">MAIN</a></p>
                    <p id="introduce" class="mainHover"><a href="<%=request.getContextPath()%>/goIntroduce">INTRODUCE</a></p>
                    <p id="notice" class="mainHover"><a href="<%=request.getContextPath()%>/notice">NOTICE</a></p>
                    <%if(loginMember!=null&&loginMember.getUserId().equals("admin")){ %>
                    <p id="myPage" class="mainHover"><a href="<%=request.getContextPath()%>/admin/manageUser">ADMIN PAGE</a></p>
                    <%}else if(loginMember!=null&&loginMember.isUserAppr()){ %>
                    <p id="myPage" class="mainHover"><a href="<%=request.getContextPath()%>/store/main">STORE PAGE</a></p>
                    <%}else if(loginMember!=null&& !loginMember.isUserAppr()) {%>
                    <p id="myPage" class="mainHover"><a href="<%=request.getContextPath()%>/mypageMain">MY PAGE</a></p>
                    <%} %>
                    <p id="service" class="mainHover"><a href="<%=request.getContextPath()%>/FAQMainServlet">FAQ</a></p>
                </div>
            </center>

            <!-- 팝메뉴 -->
		<!-- ----------로그인---------- -->
		
			
			<%
				if(loginMember != null){ 
			%>	
				<img onclick="location.replace('<%=request.getContextPath()%>/logout')" class="logoutBtn" src="<%=request.getContextPath()%>/image/logout-index.png"
					width="30px">
				<%-- <button type="button" class="logoutBtn" onclick="location.replace('<%=request.getContextPath()%>/logout')">로그아웃</button> --%>
			<%
				}else{
			%>
				<img onclick="document.getElementById('openLogin').style.display='block'" id="popMenu" src="<%=request.getContextPath()%>/image/login-index.png"
					width="30px">
			<%
				}
			%>

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
						<a class="link" href="<%=request.getContextPath()%>/views/member/signUpChoice.jsp">회원가입</a>
					</li>
					<br>
					<li style="list-style: none;">
						<a class="link" href="<%=request.getContextPath()%>/views/member/findId.jsp">아이디 찾기&nbsp;</a>
						<a class="link" href="<%=request.getContextPath()%>/views/member/findPw.jsp">&nbsp;비밀번호 찾기</a></li>
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
	<%
		Cookie ck = new Cookie("loginFail", null);
		ck.setMaxAge(0);
		response.addCookie(ck);
	%>
	<%} %>

            <!-- 미들바 지움!! -->

        </nav>

    </header>

    <video id="video" preload="auto" autoplay="true" loop="loop" muted="muted" volume="0" 
    style="position: absolute; top: 0px; left: 0px; width: 1366px; height: 768px; z-index: -2; overflow: hidden;">
        <source src="image/main.mp4">
    </video>
    
    <img src="image/back.png" alt="" id="back" width="30px">
    <img src="image/next.png" alt="" id="next" width="30px">

    <div id="slide">
        <input type="radio" name="pos" id="pos1" class="pos">
        <input type="radio" name="pos" id="pos2" class="pos" checked>

        <ul id="slideMove">
            <li class="slideAttr"> <!-- 즐겨찾기 -->
                <img src="image/즐겨찾기-1.png" alt="" id="favorite">
                <div id="clock"></div>
                <!-- <div id="addFavorite">
                    추가하기
                </div> -->
                <div id="favoriteLine">
                	<form action="<%=request.getContextPath() %>/storeView" method="post" onclick="submit()">
	                    <div class="favoritePc">
	                    </div>
                    </form>
                	<form action="<%=request.getContextPath() %>/storeView" method="post" onclick="submit()">
	                    <div class="favoritePc">
	                    </div>
                    <form action="<%=request.getContextPath() %>/storeView" method="post" onclick="submit()">
	                    <div class="favoritePc">
	                    </div>
                    </form>
                    <form action="<%=request.getContextPath() %>/storeView" method="post" onclick="submit()">
	                    <div class="favoritePc">
	                    </div>
                    </form>
                    <form action="<%=request.getContextPath() %>/storeView" method="post" onclick="submit()">
	                    <div class="favoritePc">
	                    </div>
                    </form>
                   	<form action="<%=request.getContextPath() %>/storeView" method="post" onclick="submit()">
	                    <div class="favoritePc">
	                    </div>
                    </form>
                </div>
            </li>
            <li class="slideAttr"> <!-- 메인검색 -->
                <form action="totalSearch" method="get" id="searchForm" onSubmit="false">
                        <div id="searchBoxGra">
                            <input id="searchBox" type="text" name="searchBox" placeholder="빈시트 검색" onchange="change()">
                            <input type="submit" value="검색">
                        </div>
                        <img id="moniter" src="image/main-test.png" alt="">
                </form>
                
                
            </li>
        </ul>
    </div>

    <script type="text/javascript" src="<%=request.getContextPath() %>/js/index.js?ver=1"></script>
    
    <%if(loginMember != null)  {%>
	    <script>
	    // 처음 뜨면 로그인 멤버 검사해서
	    // 로그인 안되있으면 즐겨찾기 화살표 없애기
	    
			// 즐겨찾기 불러오기
			$(function() {
				
				console.log("된다");
				
				if(<%=loginMember.getUserId()!=null%>) {
					
					$.ajax({
						url:"<%=request.getContextPath()%>/favorite/favoriteList",
						type:"post",
						data:{"userId":"<%=loginMember.getUserId()%>"},
						dataType:"json",
						success:function(data) {
							let num = 0;
		 					for(let i=0; i<data.length; i++) {
 								if(data[i].length != 0) {
 									num++;
									console.log("즐겨찾기 값 있음!" + (i+1));
									const img = $("<img>");
									img.attr({"class":"favoriteLogo", "src":data[i][0], "alt":data[i][2]});
									$(".favoritePc").eq(i).append(img);
									
									// getParameter로 받는거 3개.
									// storeId, userId, searchText
									// 만들어줘야함 -> hidden
									
									$(".favoritePc").eq(i).append($("<input>").attr({"name":"userId", "type":"hidden", "value":"<%=loginMember.getUserId()%>"}));
									$(".favoritePc").eq(i).append($("<input>").attr({"name":"storeId", "type":"hidden", "value":data[i][1]}));
									$(".favoritePc").eq(i).append($("<input>").attr({"name":"searchText", "type":"hidden", "value":data[i][2]}));
									console.log(data[i][1]);
									
									// list2.add(rs.getString("STORE_LOGO")); --> logo 0
									// list2.add(rs.getString("STORE_ID")); --> storeId 1
									// list2.add(rs.getString("STORE_NAME")); --> storeName 2
									
								} else {
									console.log("즐겨찾기 값 없음!");
									break;
								}
							}
		 					for(let i=num; i<6; i++) {
		 						$(".favoritePc").eq(i).append($("<span>").attr("class","favoriteNone").html("즐겨찾기를<br>추가해주세요."));
		 					}
						}
					})
				}
			})
	    </script>
	<%} else { %>
		<script>
				// 슬라이드 기능 제한적 이용하게 하기
				
				// 강사님한테 물어보기
				var slideNum = 0;
				
				$("#next").click(function() {
					slideNum = 1;
					console.log(slideNum);
				})
				$("#back").click(function() {
					slideNum = 0;
					console.log(slideNum);
				})
				
				$("#back").css("visibility","hidden");
				$("#next").css("visibility","hidden");
		</script>
	<%} %>
	
</body>

</html>