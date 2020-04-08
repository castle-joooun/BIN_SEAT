//메인로고 바뀌게
var transLogo = document.getElementById("transLogo");
var mainLogo = document.getElementById("mainLogo");

transLogo.addEventListener("mouseover", trans);
function trans() { //바뀜
	mainLogo.setAttribute("src", "image/binseat500-1.png");
}
transLogo.addEventListener("mouseleave", leave);
function leave() { //돌아옴
	mainLogo.setAttribute("src", "image/binseat500-2.png");
}

//메인메뉴 클릭 -> 서브메뉴 나타나게
//main
var menubarMain = document.getElementById("main");
var mainSub = document.getElementById("mainSub");
menubarMain.onclick = function () {
	mainSub.toggle
}


// 엔터키
function enterkey(){
	if(window.event.keyCode == 13){
		if(document.getElementById("uId").value != "" && document.getElementById("pw").value != ""){
			document.getElementById("loginData").submit();
		}else if(document.getElementById("uId").value == ""){
			alert("아이디를 입력하세요.");
		}else if(document.getElementById("pw").value == ""){
			alert("비밀번호를 입력하세요.");
		}
	}
}

//로그인
function checkMember(){
	if(document.getElementById("uId").value != "" && document.getElementById("pw").value != ""){
		document.getElementById("loginData").submit();
	}else if(document.getElementById("uId").value == ""){
		alert("아이디를 입력하세요.");
	}else if(document.getElementById("pw").value == ""){
		alert("비밀번호를 입력하세요.");
	}
}


