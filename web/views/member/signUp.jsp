<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="shortcut icon" href="#">
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사용자용 회원가입</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/signUp.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/member/signUp_terms.css" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/js/jquery-3.4.1.min.js"></script>

</head>

<body>
    <div>
        <form id="myForm" action="<%=request.getContextPath()%>/signUpFinish" method="post">
            <div class="container">
                <h1 style="text-align: center;">사용자용 회원가입</h1>

                <hr class="line">
                <p style="text-align: right;">*은 필수입력 항목입니다.</p>
                <label for="id">
                    <b>*아이디</b><span class="span" id="idCondition"></span>
                </label>
                <input type="text" class="enrollInput" placeholder="한글 제외 5자 이상 15자 이내" id="userId" name="id" >

                <label for="psw"><b>*비밀번호</b><span class="span" id="pwCondition"></span></label>
                <input type="password" class="enrollInput" placeholder="영어와 숫자, 특수문자(!@#$%^&*)만 사용 가능합니다" id="pw1" name="psw"
                    onchange="pwCondition()">

                <label for="pswcheck"><b>*비밀번호 확인</b><span class="span" id="pwOk"></span></label>
                <input type="password" class="enrollInput" placeholder="위의 비밀번호와 같이 입력하세요" id="pw2" name="pswcheck" onchange="pswCheck()">

                <label for="name"><b>*이름</b><span class="span" id="nameCondition"></span></label>
                <input type="text" class="enrollInput" placeholder="이름" id="userName" name="name" onchange="checkName();">

                <label for="email"><b>*이메일</b><span class="span" id="emailCondition"></span></label>
                <input type="email" class="enrollInput" placeholder="이메일" id="userEmail" name="email">

                <label for="phone"><b>*전화번호</b><span class="span" id="phoneCondition"></span></label>
                <input type="text" class="enrollInput" placeholder="'-' 없이 입력해주세요" id="userPhone" name="phone" onchange="phoneCondition();">

                <label for="address"><b>*주소</b></label>
				<input type="text" class="postNum" id="sample4_postcode" placeholder="우편번호" readonly="readonly">
				<input type="button" onclick="findAddress()" value="우편번호 찾기"><br>
				<input type="text" class="enrollInput" id="sample4_roadAddress" name="address1" placeholder="도로명주소" readonly="readonly">
				<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" readonly="readonly">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" class="enrollInput" id="sample4_detailAddress" name="address2" placeholder="상세주소">
			 	<input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
                <label for="gender"><b>성별</b></label>
                <select name="gender" id="userGender">
                    <option value="성별">성별 선택</option>
                    <option value="남">남</option>
                    <option value="여">여</option>
                </select>


                <p>계정 생성 시 이용약관에 동의하는 것으로 간주됩니다.<button type="button" id="termsCheck"
                        onclick="document.getElementById('terms').style.display='block'"
                        style="width:auto;">이용약관</button></p>

                <div class="clearfix">
                    <button type="button" onclick="signUpPass()" class="signupbtn">회원가입</button>
                    <a href="<%=request.getContextPath()%>/views/member/signUpChoice.jsp">
                        <button type="button" class="cancelbtn">
                            취소
                        </button>
                    </a>
                </div>
            </div>
        </form>
    </div>


    <div id="terms" class="modal">
        <div id="modal-content">
            <div class="terms-container">
                <span onclick="document.getElementById('terms').style.display='none'" class="close"
                    title="Close Modal">&times;</span>
                <h2>이용약관</h2>
            </div>
            <div class="modal-body">
                <h4>제1조 (목적)</h4>
                <p>이 약관은 (주)아임웹 (이하 "회사")가 제공하는 아임웹 서비스 및 관련 제반 서비스(이하
                    "서비스")의 이용과 관련하여 회사와 회원과의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
                <h4>제2조 (정의)</h4>
                <p>이 약관에서 사용하는 용어는 아래와 같습니다.</p>
                <ol class="description-list">
                    <li>"회원"이라 함은 회사의 "서비스"에 접속하여 이 약관에 따라 "회사"와 이용계약을 체결하고 "회사"가 제공하는 "서비스"를 이용하는 고객을
                        말합니다.</li>
                    <li>"아이디(ID)"라 함은 "회원"의 식별과 서비스 이용을 위하여 "회원"이 정하고 "회사"가 승인하는 문자와 숫자의 조합을 의미합니다.</li>
                    <li>"비밀번호"라 함은 "회원"이 부여 받은 "아이디와 일치되는 "회원"임을 확인하고 비밀보호를 위해 "회원" 자신이 정한 문자 또는 숫자의 조합을
                        의미합니다.</li>
                    <li>"유료서비스"라 함은 "서비스" 중 "회사"가 유료로 제공하는 각종 웹사이트 구축 서비스 및 제반 서비스를 의미합니다.</li>
                    <li>"게시물" 혹은 "콘텐츠"이라 함은 회원이 서비스를 이용함에 있어 서비스상에 게시한 부호ᆞ문자ᆞ음성ᆞ음향ᆞ화상ᆞ동영상 등의 정보 형태의 글,
                        사진, 동영상 및 각종 파일과 링크 등을 의미합니다.</li>
                </ol>
                <h4>제3조 (약관의 게시와 개정)</h4>
                <ol class="description-list">
                    <li>"회사"는 이 약관의 내용을 "회원"이 쉽게 알 수 있도록 서비스 초기 화면에 게시합니다.</li>
                    <li>"회사"는 "약관의규제에관한법률", "정보통신망이용촉진및정보보호에관한법률(이하 "정보통신망법")" 등 관련법을 위배하지 않는 범위에서 이 약관을
                        개정할 수 있습니다.</li>
                </ol>
                <h4>제4조 (약관의 해석)</h4>
                <ol class="description-list">
                    <li>회사는 개별 서비스에 대해서는 별도의 이용약관 및 정책("기타 약관 등"이라 함)을 둘 수 있으며, 해당 내용이 이 약관과 상충할 경우에는
                        "기타 약관 등"이 우선하여 적용됩니다.</li>
                    <li>이 약관에서 정하지 아니한 사항이나 해석에 대해서는 "기타 약관 등" 및 관계법령 또는 상관례에 따릅니다.</li>
                </ol>
                <h4>제5조 [이용계약 체결]</h4>
                <ol class="description-list">
                    <li>이용계약은 "회원"이 되고자 하는 자(이하 "가입신청자")가 약관의 내용에 대하여 동의를 한 다음 회원가입신청을 하고 "회사"가 이러한 신청에
                        대하여 승낙함으로써 체결됩니다.</li>
                    <li>"회사"는 "가입신청자"의 신청에 대하여 서비스 이용을 승낙함을 원칙으로 합니다. 다만, "회사"는 다음 각 호에 해당하는 신청에 대하여는
                        승낙을 하지 않거나 사후에 이용계약을 해지할 수 있습니다.</li>
                    <ul>
                        <li>가입신청자가 이 약관에 의하여 이전에 회원자격을 상실한 적이 있는 경우</li>
                        <li>실명이 아니거나 타인의 명의를 이용한 경우</li>
                        <li>허위의 정보를 기재하거나, 회사가 제시하는 내용을 기재하지 않은 경우</li>
                        <li>14세 미만 아동이 정보통신망 이용촉진 및 정보보호등에 관한 법률에서 정한 "개인정보" 입력 시 법정대리인(부모 등)의 동의를 얻지
                            아니한 경우</li>
                        <li>이용자의 귀책사유로 인하여 승인이 불가능하거나 기타 규정한 제반 사항을 위반하며 신청하는 경우</li>
                    </ul>
                    <li>"회원"이 유료서비스를 이용하는 경우 이용대금을 납부한 후 서비스를 이용하는 것을 원칙으로 하며, 유료서비스 이용계약의 성립시기는 "구매완료"를
                        신청절차 상에서 표시한 시점으로 합니다.</li>
                    <li>"회사"는 만 20세 미만의 미성년회원이 유료서비스를 이용하고자 하는 경우에 부모 등 법정대리인의 동의를 얻거나, 계약체결
                        후 추인을 얻지 않으면 미성년자 본인 또는 법정대리인이 그 계약을 취소할 수 있다는 내용을 계약체결 전에 고지하는 조치를
                        취합니다.</li>
                    <li>"회사"는 서비스관련설비의 여유가 없거나, 기술상 또는 업무상 문제가 있는 경우에는 승낙을 유보할 수 있습니다.</li>
                    <li>제2항과 제3항에 따라 회원가입신청의 승낙을 하지 아니하거나 유보한 경우, "회사"는 원칙적으로 이를 가입신청자에게 알리도록 합니다.</li>
                    <li>이용계약의 성립 시기는 "회사"가 가입완료를 신청절차 상에서 표시한 시점으로 합니다.</li>
                    <li>"회사"는 "회원"에 대해 회사정책에 따라 등급별로 구분하여 이용시간, 이용횟수, 서비스 메뉴 등을 세분하여 이용에 차등을 둘 수 있습니다.
                    </li>
                </ol>
                <h4>제6조 (회원정보의 변경)</h4>
                <ol class="description-list">
                    <li>"회원"은 개인정보관리화면을 통하여 언제든지 본인의 개인정보를 열람하고 수정할 수 있습니다. 다만, 서비스 관리를 위해 필요한 아이디 등은
                        수정이 불가능합니다.</li>
                    <li>"회원"은 회원가입신청 시 기재한 사항이 변경되었을 경우 온라인으로 수정을 하거나 전자우편 기타 방법으로 "회사"에 대하여 그 변경사항을 알려야
                        합니다.</li>
                    <li>제2항의 변경사항을 "회사"에 알리지 않아 발생한 불이익에 대하여 "회사"는 책임지지 않습니다.</li><br>
                </ol>
                <h4>제7조 (개인정보보호 의무)</h4>
                <p>"회원이" "회사"의 "서비스"에 제공하는 정보 일체는 개인정보처리방침에 따르며, "회사"가 귀하의 정보를 수집 및 이용하는 행위는 동 정책에 따라
                    규율됩니다. "회원"은 본 서비스를 이용함으로써 "회사"가 "회원"의 정보를 수집 및 이용(개인정보처리방침에서 규정하는 바와 같음)하는 것에 동의하는
                    것으로 간주된다는 사실을 인지하고 있습니다. "회원"에게 본 서비스를 제공하는 것의 일환으로, "회사"는 서비스 관련 공지사항 및 행정적 사안에 관한
                    메시지를 비롯한 전달사항을 제공할 필요가 있을 수 있습니다.</p>
                <h4>제8조 ("회원"의 "아이디" 및 "비밀번호"의 관리에 대한 의무)</h4>
                <p>"회원"은 본 서비스에서 사용하는 비밀번호와 관련된 모든 행동을 보호/관리해야 합니다. "회사"는 귀하의 계정에 비밀번호를 강력한 암호(대문자, 소문자
                    및 숫자, 기호의 조합)로 설정할 것을 권장합니다. "회사"는 위 사항을 만족하지 못했을 때 일어난 어떠한 손실이나 피해에 대해 책임지지 않습니다.
                    "회사"는 "서비스"를 안전하게 유지하기 위해 최선을 다하고 있지만 안전을 보장할 수는 없습니다.</p>
                <h4>제9조 ("회원"에 대한 통지)</h4>
                <ol class="description-list">
                    <li>"회사"가 "회원"에 대한 통지를 하는 경우 본 약관에 별도 규정이 없는 한 "회원"이 지정한 전자우편주소, 서비스 내 전자메모 및 쪽지 등으로
                        할 수 있습니다.</li>
                    <li>"회사"는 "회원" 전체에 대한 통지의 경우 7일 이상 "회사"의 게시판에 게시함으로써 제1항의 통지에 갈음할 수 있습니다.</li>
                </ol>
                <h4>제10조 ("회사"의 의무)</h4>
                <ol class="description-list">
                    <li>"회사"는 관련법과 본 약관이 금지하거나 미풍양속에 반하는 행위를 하지 않으며, 계속적이고 안정적으로 "서비스"를 제공하기 위하여 최선을 다하여
                        노력합니다.</li>
                    <li>"회사"는 "회원"이 안전하게 "서비스"를 이용할 수 있도록 개인정보(신용정보 포함)보호를 위해 보안시스템을 갖추어야 하며 개인정보보호정책을
                        공시하고 준수합니다.</li>
                    <li>"회사"는 서비스이용과 관련하여 "회원"으로부터 제기된 의견이나 불만이 정당하다고 인정할 경우에는 이를 처리하여야 합니다. 회원이 제기한
                        의견이나 불만사항에 대해서는 게시판을 활용하거나 전자우편 등을 통하여 "회원"에게 처리과정 및 결과를 전달합니다.</li>
                    <li>"회사"는 다음의 사항을 해당 " 유료서비스"의 이용초기화면이나 그 포장, FAQ 등에 "회원"이 알기 쉽게 표시합니다.
                        <ul>
                            <li>유료서비스의 명칭 또는 제호</li>
                            <li>유료서비스의 내용, 이용방법, 이용료, 결제방법 기타 이용조건</li>
                            <li>이용가능 기기 및 이용에 필요한 최소한의 기술사양</li>
                        </ul>
                    </li>
                </ol>
                <h4>제11조 (서비스 이용의 중지 또는 계약의 해지)</h4>
                <ol class="description-list">
                    <li>"회원"은 다음 행위를 하여서는 안 됩니다.</li>
                    <ul>
                        <li>신청 또는 변경 시 허위내용의 등록</li>
                        <li>타인의 정보도용</li>
                        <li>"회사"에 게시된 정보의 변경</li>
                        <li>"회사"가 정한 정보 이외의 정보(컴퓨터 프로그램 등) 등의 송신 또는 게시</li>
                        <li>"회사"와 기타 제3자의 저작권 등 지적재산권에 대한 침해</li>
                        <li>"회사" 및 기타 제3자의 명예를 손상시키거나 업무를 방해하는 행위</li>
                        <li>외설 또는 폭력적인 메시지, 화상, 음성, 기타 공서양속에 반하는 정보를 "회사"에 공개 또는 게시하는 행위</li>
                        <li>검색엔진 스팸, 도어웨어 페이지, 타 서비스와 매우 유사한 페이지 등 내용이 현저히 저급하거나 빈약하고 사용자에게 뚜렷한 부가 가치를
                            제공하지 않는 행위</li>
                        <li>회사에서 별도 규정한 콘텐츠 원칙에 어긋나거나, 성격에 부합하지 않는 행위</li>
                        <li>기타 불법적이거나 부당한 행위</li>
                    </ul>
                    <li>"회원"은 관계법, 본 약관의 규정, 이용안내 및 "서비스"와 관련하여 공지한 주의사항, "회사"가 통지하는 사항 등을 준수하여야 하며, 기타
                        "회사"의 업무에 방해되는 행위를 하여서는 안 됩니다.</li>
                </ol>
                <h4>제12조 (서비스의 제공 등)</h4>
                <ol class="description-list">
                    <li>서비스는 연중무휴, 1일 24시간 제공함을 원칙으로 합니다.</li>
                    <li>"회사"는 컴퓨터 등 정보통신설비의 보수점검, 교체 및 고장, 통신두절 또는 운영상 상당한 이유가 있는 경우 서비스의
                        제공을 일시적으로 중단할 수 있습니다. 이 경우 "회사"는 제9조("회원"에 대한 통지)에 정한 방법으로 "회원"에게 통지합니다.
                        다만, "회사"가 사전에 통지할 수 없는 부득이한 사유가 있는 경우 사후에 통지할 수 있습니다.</li>
                    <li>"회사"는 서비스의 제공에 필요한 경우 정기점검을 실시할 수 있으며, 정기점검시간은 서비스제공화면에 공시한 바에 따릅니다.</li>
                    <li>사업종목의 전환, 사업의 포기 등의 이유로 "유료서비스"를 제공할 수 없게 되는 경우에는 "회사"는 제9조(회원에 대한 통지)에서 정한 방법으로
                        "회원"에게 통지하고 당초 "회사"에서 제시한 조건에 따라 "회원"에게 보상합니다.</li>
                </ol>
                <h4>제13조 (서비스의 변경)</h4>
                <ol class="description-list">
                    <li>"회사"는 상당한 이유가 있는 경우에 운영상, 기술상의 필요에 따라 제공하고 있는 전부 또는 일부 서비스를 변경할 수
                        있습니다. 서비스의 내용, 이용방법, 이용시간에 대하여 변경이 있는 경우에는 변경사유, 변경될 서비스의 내용 및 제공일자 등은 그
                        변경 전 7일 이상 해당 서비스 초기화면에 게시하여야 합니다.</li>
                    <li>"회사"는 무료로 제공되는 서비스의 일부 또는 전부를 회사의 정책 및 운영의 필요상 수정, 중단, 변경할 수 있으며, 이에 대하여 관련법에
                        특별한 규정이 없는 한 회원에게 별도의 보상을 하지 않습니다.</li>
                    <li>"유료서비스"의 변경 내용이 중대하거나 "회원"에게 불리한 경우에는 "회사"가 해당 "유료서비스"를 제공받는 "회원"에게
                        제9조(회원에 대한 통지)에서 정한 방법으로 "회원"에게 통지합니다. 이때, "회사"는 동의를 거절한 "회원"에 대해서는 변경 전
                        서비스를 제공합니다. 다만, 그러한 서비스 제공이 불가능할 경우 해당 서비스의 제공을 중지하거나 계약을 중단할 수 있으며 이
                        경우 환불은 제19조 2항에 따라 진행됩니다.</li>
                </ol>
                <h4>제14조 (정보의 제공)</h4>
                <ol class="description-list">
                    <li>"회사"는 "회원"이 서비스 이용 중 필요하다고 인정되는 다양한 정보를 공지사항이나 전자우편 등의 방법으로 "회원"에게
                        제공할 수 있습니다. 다만, "회원"은 관련법에 따른 거래관련 정보, 고객센터 답변 등을 제외하고 언제든지 전자우편 등을 통하여
                        수신 거절을 할 수 있습니다.</li>
                    <li>제1항의 정보를 전화 및 모사전송기기에 의하여 전송하려고 하는 경우에는 "회원"의 사전 동의를 받아서 전송합니다.</li>
                </ol>
                <h4>제15조 (게시물의 저작권)</h4>
                <ol class="description-list">
                    <li>"회원"이 서비스 내에 게시한 게시물의 저작권은 해당 게시물의 저작자에게 귀속됩니다.</li>
                    <li>"회원"이 "서비스" 내에 게시하는 "콘텐츠"는 검색결과 내지 "서비스" 및 관련 프로모션 등에 노출될 수 있으며, 해당
                        노출을 위해 필요한 범위 내에서는 일부 수정, 복제, 편집되어 게시될 수 있습니다. 이 경우, 회사는 저작권법 규정을 준수하며,
                        "회원"은 언제든지 고객센터 또는 "서비스" 내 관리기능을 통해 해당 게시물에 대해 삭제, 검색결과 제외, 비공개 등의 조치를
                        취할 수 있습니다.</li>
                    <li>"회사"는 제2항 이외의 방법으로 회원의 게시물을 이용하고자 하는 경우에는 전화, 팩스, 전자우편 등을 통해 사전에 회원의 동의를 얻어야
                        합니다.</li>
                </ol>
                <h4>제16조 (게시물의 관리)</h4>
                <ol class="description-list">
                    <li>"회원"의 게시물이 "정보통신망법" 및 "저작권법"등 관련법에 위반되는 내용을 포함하는 경우, 권리자는 관련법이 정한
                        절차에 따라 해당 게시물의 게시중단 및 삭제 등을 요청할 수 있으며, "회사"와 "회원"은 관련법에 따라 조치를 취하여야 합니다.</li>
                    <li>"회사"는 전항에 따른 권리자의 요청이 없는 경우라도 권리침해가 인정될 만한 사유가 있거나 기타 회사 정책 및 관련법에 위반되는 경우에는
                        관련법에 따라 해당 게시물에 대해 임시조치 등을 취할 수 있습니다.</li>
                </ol>
                <h4>제17조 (권리의 귀속)</h4>
                <ol class="description-list">
                    <li>"서비스"에 대한 저작권 및 지적재산권은 회사에 귀속됩니다. 단, 회원의 게시물 및 제휴계약에 따라 제공된 저작물 등은 제외합니다.</li>
                    <li>"회사"는 서비스와 관련하여 회원에게 회사가 정한 이용조건에 따라 계정, 아이디, 콘텐츠 등을 이용할 수 있는 이용권만을 부여하며, "회원"은
                        이를 양도, 판매, 담보제공 등의 처분행위를 할 수 없습니다.</li>
                </ol>
                <h4>제18조 ("회원"의 계약해제, 해지 등)</h4>
                <ol class="description-list">
                    <li>"회원"은 언제든지 자기 정보 관리 메뉴를 통하여 서비스 탈퇴 신청을 할 수 있으며, "회사"는 관련법 등이 정하는 바에 따라 이를 즉시
                        처리하여야 합니다.</li>
                    <li>"회원"이 계약을 해지할 경우, 관련법 및 개인정보보호정책에 따라 회사가 회원정보를 보유하는 경우를 제외하고는 해지 즉시 회원의 모든 데이터는
                        소멸됩니다.</li>
                    <li>"회원"이 계약을 해지하는 경우, 본인 계정에 등록된 모든 데이터 일체는 삭제됩니다 .</li>
                </ol>
                <h4>제19조 (환불)</h4>
                <ol class="description-list">
                    <li>"회사"는 약정된 "유료서비스"의 하자를 회사가 보완, 수정할 수 없어 서비스가 이루어지지 않거나 정상적 이용이 불가능한 경우 회원이 결제한
                        전액을 환불합니다.</li>
                    <li>"회사"는 제13조 4항, 제18조 및 제20조에 따른 계약해지가 발생하거나, "회원"이 "유료서비스" 이용계약을 해지할 수 있으나, <a
                            href="https://imweb.me/faq?mode=view&page=0&category=30&category2=46&idx=233"
                            target="_blank" class="text-primary">법적으로 요구되지 않는 한</a> 요금은 환불되지 않습니다.</li>
                    <li>"회사"는 과오금이 발생한 경우 이용대금의 결제와 동일한 방법으로 과오금 전액을 환불하여야 하며 "회사"가 과오금에 대한
                        환불을 거부할 경우 "회사"는 정당하게 이용대금이 부과되었음을 입증할 책임을 집니다.</li>
                    <li>회사는 결제와 동일한 방법으로 환불을 진행하며, 결제와 동일한 방법으로 환불이 불가능한 때는 이를 사전에 고지합니다. 다만, 수납확인이 필요한
                        결제수단의 경우에는 수납확인일로부터 3영업일 이내에 이를 환불하도록 합니다.</li>
                    <li>환불 진행 시, 환불 금액 정산은 다음과 같은 규칙으로 진행됩니다.
                        <ul class="description-list">
                            <li>할인율 적용은 계약 기간을 정상적인 만기 형태로 이용한다는 전제로 제공된 것이며 중도 환불 시 한일율 적용 금액이 아닌 표준 금액
                                요금으로 정산됩니다.</li>
                            <li>할인율 적용이 아닌 표준 금액의 월 요금 기준으로 일할 계산 후 선납한 이용요금에서 사용된 기간의 금액을 제외한 잔액의 90%를
                                정산합니다.</li>
                            <li>환불 신청일로부터 15일 이내 환불 조치 됩니다.</li>
                            <li>환불되지 않는 10%는 고객 귀책 사유로 인해 서비스를 해지한 위약금으로 처리됩니다.</li>
                            <li>단, 정산된 환불 잔액의 90%가 0원인 경우 환불하지 않습니다.</li>
                        </ul>
                    </li>
                </ol>
                <h4>제20조 (이용제한 등)</h4>
                <ol class="description-list">
                    <li>"회사"는 "회원"이 본 약관의 의무를 위반하거나 서비스의 정상적인 운영을 방해한 경우, 서비스 이용을 경고, 일시정지, 계약해지로 단계적으로
                        제한할 수 있습니다 .</li>
                    <li>"회사"는 전항에도 불구하고, "주민등록법"을 위반한 명의도용 및 결제도용, "저작권법" 및 "컴퓨터프로그램보호법"을
                        위반한 불법프로그램의 제공 및 운영방해, "정보통신망법"을 위반한 불법통신 및 해킹, 악성프로그램의 배포, 접속권한 초과행위 등과
                        같이 관련법을 위반한 경우에는 즉시 계약해지를 할 수 있습니다. 본 항에 따른 계약해지 시 서비스 이용을 통해 획득한 혜택 등도
                        모두 소멸되며, 회사는 이에 대해 별도로 보상하지 않습니다.</li>
                    <li>"회사"는 무료회원이 계속해서 3개월 이상 로그인하지 않는 경우, 회원정보의 보호 및 운영의 효율성을 위해 이용을 제한할 수 있습니다 .
                    </li>
                    <li>회사는 본 조의 이용제한 범위 내에서 제한의 조건 및 세부내용은 이용제한정책 등에서 정한 바에 의합니다 .</li>
                    <li>본 조에 따라 서비스 이용을 제한하거나 계약을 해지하는 경우에는 "회사"는 제9조["회원"에 대한 통지]에 따라 통지합니다.</li>
                    <li>"회원"은 본 조에 따른 이용제한 등에 대해 "회사"가 정한 절차에 따라 이의신청을 할 수 있습니다. 이 때 이의가 정당하다고 회사가 인정하는
                        경우 회사는 즉시 서비스의 이용을 재개합니다.</li>
                </ol>
                <h4>제21조 (서비스 일시중지 등에 의한 회원 피해보상)</h4>
                <p>"회사"는 "유료서비스"를 고객에게 제공하는 기간 동안 최대의 월별 가동 시간을 제공하기 위해 합리적인 노력을 기울여야 합니다. “유료서비스"에 포함된
                    서비스 책임을 충족하지 못할 경우, 고객은 하기 정의된 서비스 보상안을 고객지원 등을 통해 청구할 수 있습니다. 단, 사전에 고지된 정기점검 등의
                    시간은 월별 가동시간 산정에 포함되지 않습니다.</p>
                <ol class="description-list">
                    <li>월별 가동 중지 시간 3시간 이상 발생시 최근 3개월(3개월 미만인 경우 해당 기간 적용)의 1일 평균 요금에 서비스 제공중지시간을 24로 나눈
                        수를 곱하여 산출한 금액의 5배 범위 내에서 협의 후 배상합니다.</li>
                    <li>보상은 보상안 협의 완료 후 3영업일 이내에 지급합니다.</li>
                </ol>
                <h4>제22조 (책임의 한계)</h4>
                <ol class="description-list">
                    <li>"회사"는 천재지변 또는 이에 준하는 불가항력으로 인하여 "서비스"를 제공할 수 없는 경우에는 "서비스" 제공에 관한 책임이 면제됩니다.
                    </li>
                    <li>"회사"는 "회원" 의 귀책사유로 인한 서비스 이용의 장애에 대하여는 책임을 지지 않습니다.</li>
                    <li>"회사"는 "회원"이 "서비스"와 관련하여 게재한 정보, 자료, 사실의 신뢰도, 정확성 등의 내용에 관하여는 책임을 지지 않습니다.</li>
                    <li>"회사"는 "회원" 간 또는 "회원"과 제3자 상호간에 "서비스"를 매개로 하여 거래 등을 한 경우에는 책임이 면제됩니다.</li>
                    <li>"회사"는 무료로 제공되는 서비스 이용과 관련하여 관련법에 특별한 규정이 없는 한 책임을 지지 않습니다.</li>
                    <li>“회사"는 "회원"이 "서비스"를 이용하여 기대하는 수익을 보장하지 않습니다. 또한 이와 관련된 손해는 배상하지 않습니다.</li>
                </ol>
                <h4>제23조 (준거법 및 재판관할)</h4>
                <ol class="description-list">
                    <li>"회사"와 "회원" 간 제기된 소송은 대한민국법을 준거법으로 합니다.</li>
                    <li>"회사"와 "회원"간 발생한 분쟁에 관한 소송은 민사소송법 상의 관할법원에 제소합니다.</li>
                </ol>
                <h4>부칙</h4>
                <ol>
                    <li>공지일자: 2020년 2월 13일</li>
                    <li>적용일자: 2020년 3월 13일</li>
                </ol>

            </div>
        </div>
    </div>

    <script src="<%=request.getContextPath()%>/js/signUp.js?1234"></script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	
	// 주소 API
	function findAddress() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                //document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                //var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                //if(data.autoRoadAddress) {
                //    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                //   guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                //   guideTextBox.style.display = 'block';

                //} else if(data.autoJibunAddress) {
                //    var expJibunAddr = data.autoJibunAddress;
                //    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                //   guideTextBox.style.display = 'block';
                //} else {
                //    guideTextBox.innerHTML = '';
                //    guideTextBox.style.display = 'none';
                //}
            }
        }).open();
    }
    
</script>
	
</body>

</html>