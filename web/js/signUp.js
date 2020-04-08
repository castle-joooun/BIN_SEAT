var flag1;
//아이디 조건
$(function(){
	$("#userId").keyup(function(){
		$.ajax({
			url : "/20AM_EMPTY_SEAT/checkIdDupplicate",
			type : "post",
			data : {"id" : $(this).val()},
			success : function(data){
				if(!data.isUseable){
					idCondition.innerHTML = "사용중인 아이디입니다.";
					idCondition.style.color = "red";
					flag1=  false;
				}else {
					idCondition.innerHTML = "유효한 아이디입니다.";
					idCondition.style.color = "blue";
					flag1=  true;
				}
			}
		})
	})
	$("#userId").blur(function(){
		var uId = document.getElementById("userId").value;
		var idCondition = document.getElementById("idCondition");
		var condition = /^[A-za-z0-9]{5,15}/g;
		if (!condition.test(uId)) {
			idCondition.innerHTML = "※아이디는 영문, 숫자 5~15자 사이만 가능합니다.";
			idCondition.style.color = "red";
			flag1= false;
		}
	})
})


//비밀번호 확인 
function pswCheck() {
	var pw1 = document.getElementById("pw1").value;
	var pw2 = document.getElementById("pw2").value;
	var pwOk = document.getElementById("pwOk");
	if (pw1 != '' && pw2 != '') {
		if (pw1 == pw2) {
			pwOk.innerHTML = "비밀번호가 일치합니다.";
			pwOk.style.color = "blue";
			return true;
		} else {
			pwOk.innerHTML = "※비밀번호가 일치하지 않습니다.";
			pwOk.style.color = "red";
			return false;
		}
	}
}

//비밀번호 조건
function pwCondition() {
	var pw1 = document.getElementById("pw1").value;
	var pwCondition = document.getElementById("pwCondition");
	var condition = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	if (condition.test(pw1)) {
		pwCondition.innerHTML = "조건이 일치합니다.";
		pwCondition.style.color = "blue";
		return true;
	} else {
		pwCondition.innerHTML = "※조건이 일치하지 않습니다.";
		pwCondition.style.color = "red";
		return false;
	}
}

//이름 확인
function checkName() {
	var name = document.getElementById("userName").value;
	var nameCondition = document.getElementById("nameCondition");
	var condition = /^[가-힣]{2,}$/;
	if (condition.test(name)) {
		nameCondition.innerHTML = "";
		return true;
	} else {
		nameCondition.innerHTML = "※한글이름 두글자 이상 입력하세요.";
		nameCondition.style.color = "red";
		return false;
	}
}

var flag2;
$(function(){
	$("#userEmail").keyup(function(){
		$.ajax({
			url : "/20AM_EMPTY_SEAT/checkEmailDupplicate",
			type : "post",
			data : {"email" : $(this).val()},
			success : function(data){
				if(!data.isUseable){
					emailCondition.innerHTML = "사용중인 이메일입니다.";
					emailCondition.style.color = "red";
					flag2 = false;
				}else {
					emailCondition.innerHTML = "유효한 이메일입니다.";
					emailCondition.style.color = "blue";
					flag2 = true;
				}
			}
		})
	})
	$("#userEmail").blur(function(){
		var userEmail = document.getElementById("userEmail").value;
		var emailCondition = document.getElementById("emailCondition");
		var condition = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		if (!condition.test(userEmail)) {
			emailCondition.innerHTML = "※이메일 형식이 아닙니다.";
			emailCondition.style.color = "red";
			flag2 = false;
		}
	})
})

//전화번호 유효성
function phoneCondition() {
	var userPhone = document.getElementById("userPhone").value;
	var phoneCondition = document.getElementById("phoneCondition");
	var condition = /^\d{3}\d{3,4}\d{4}$/;
	if (condition.test(userPhone)) {
		phoneCondition.innerHTML = "유효한 전화번호입니다.";
		phoneCondition.style.color = "blue";
		return true;
	} else {
		phoneCondition.innerHTML = "※전화번호 형식이 아닙니다.";
		phoneCondition.style.color = "red";
		return false;
	}
}

function enterkeyup(){
	var pswCheck = this.pswCheck();
	var pwCondition = this.pwCondition();
	var checkName = this.checkName();
	var phoneCondition = this.phoneCondition();
	var uId = document.getElementById("userId").value;
	var uName = document.getElementById("userName").value;
	var uEmail = document.getElementById("userEmail").value;
	var uPhone = document.getElementById("userPhone").value;
	var uAddress = document.getElementById("sample4_roadAddress").value;
	var uAddress2 = document.getElementById("sample4_detailAddress").value;
	var gender = document.getElementById("userGender").value;

	if(window.event.keyCode == 13){
		if (uId != "" && uName != "" && uEmail != "" && uPhone != "" && uAddress != "" && uAddress != "" && gender != ""
			&& flag1 == true && checkName == true && pswCheck == true && pwCondition == true && flag2 == true && phoneCondition == true) {
			document.getElementById("myForm").submit();
		} else if (pswCheck == false) {
			alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
		} else if (pwCondition == false) {
			alert("비밀번호 조건을 확인하세요.");
		} else if (flag1 == false) {
			alert("아이디를 확인하세요.");
		} else if (flag2 == false){
			alert("이메일을 확인하세요.");
		} else if (checkName == false) {
			alert("이름을 확인하세요.")
		} else if (phoneCondition == false) {
			alert("전화번호를 확인하세요.");
		} else if (uId == "") {
			alert("아이디를 입력하세요.");
		} else if (uName == "") {
			alert("이름을 입력하세요.");
		} else if (uEmail == "") {
			alert("이메일을 입력하세요.");
		} else if (uPhone == "") {
			alert("전화번호를 입력하세요.");
		} else if (uAddress == "") {
			alert("주소를 입력하세요.");
		} else if (uAddress2 == ""){
			alert("상세주소를 입력하세요.");
		} else if(gender == "성별"){
			alert("성별을 입력하세요.");
		}
	}
}

//회원가입조건 만족시 다음 / 아니면 얼럿
function signUpPass() {
	var pswCheck = this.pswCheck();
	var pwCondition = this.pwCondition();
	var checkName = this.checkName();
	var phoneCondition = this.phoneCondition();
	var uId = document.getElementById("userId").value;
	var uName = document.getElementById("userName").value;
	var uEmail = document.getElementById("userEmail").value;
	var uPhone = document.getElementById("userPhone").value;
	var uAddress = document.getElementById("sample4_roadAddress").value;
	var uAddress2 = document.getElementById("sample4_detailAddress").value;
	var gender = document.getElementById("userGender").value;

	if (uId != "" && uName != "" && uEmail != "" && uPhone != "" && uAddress != "" && uAddress != "" && gender != "성별" 
		&& flag1 == true && checkName == true && pswCheck == true && pwCondition == true && flag2 == true && phoneCondition == true) {
		document.getElementById("myForm").submit();
	} else if (pswCheck == false) {
		alert("비밀번호와 비밀번호 확인이 서로 다릅니다.");
	} else if (pwCondition == false) {
		alert("비밀번호 조건을 확인하세요.");
	} else if (flag1 == false) {
		alert("아이디를 확인하세요.");
	} else if (flag2 == false){
		alert("이메일을 확인하세요.");
	} else if (checkName == false) {
		alert("이름을 확인하세요.")
	} else if (phoneCondition == false) {
		alert("전화번호를 확인하세요.");
	} else if (uId == "") {
		alert("아이디를 입력하세요.");
	} else if (uName == "") {
		alert("이름을 입력하세요.");
	} else if (uEmail == "") {
		alert("이메일을 입력하세요.");
	} else if (uPhone == "") {
		alert("전화번호를 입력하세요.");
	} else if (uAddress == "") {
		alert("주소를 입력하세요.");
	} else if (uAddress2 == ""){
		alert("상세주소를 입력하세요.");
	} else if(gender == "성별"){
		alert("성별을 입력하세요.");
	}
}


//이용약관 모달창
var modal = document.getElementById('terms');

window.onclick = function (event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}
}


