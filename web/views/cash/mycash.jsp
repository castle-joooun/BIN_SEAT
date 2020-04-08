<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>

<div class="alldiv clearl">
		<div class="cashTop">
            <p class="myname"><%=loginMember.getUserName() %> 님의</p>
            <p class="mycash">My bin Cash : </p>
            <p class="mywon"><%=loginMember.getCash() %>원</p> 
        </div>
       		 
       	<form method="post" class="whatcharge"> 
		   	 <h2>충전하기</h2>
		   	 <div class="moneybox">
			     <p class="chargingmoney">충전금액</p>
				     <select name="thisismoney" id="thisismoney">
				         <option value="1000" class="thisismoney">1000원</option>
				         <option value="3000" class="thisismoney">3000원</option>
				         <option value="5000" class="thisismoney">5000원</option>
				         <option value="10000" class="thisismoney">10000원</option>
				         <option value="50000" class="thisismoney">50000원</option>
				     </select>
			 </div>
		     <h2>충전 수단</h2>
			     <label>
			     	<p class="paytag">핸드폰결제</p><input type="radio" value="phone" name="pay" class="pay">
			     </label>
			     <label>
			     	<p class="paytag">계좌이체</p><input type="radio" value="trans" name="pay" class="pay">
			     </label>
			     <label>
			 	 	<p class="paytag">카드결제</p><input type="radio" value="card" name="pay" class="pay">
			     </label>
			     <label>
			   	 	<p class="paytag">가상계좌</p><input type="radio" value="vbank" name="pay" class="pay">
			     </label>
			     
		     <img width="480px" src="https://t1.daumcdn.net/cfile/tistory/2132884156B056EE15" alt="" class="robot">
		     <input type="button" class="gomoney" value="충전하기">
    </form> 
</div>
<script>

$(".gomoney").click(function () {
	var thisismoney=parseInt($('#thisismoney option:selected').val()); //선택된 결제금액의 값
	var pay=$('input[name="pay"]:checked').val(); //선태된 결제방법의 값
	var userId="<%=loginMember.getUserId()%>";
	var IMP = window.IMP; 
	IMP.init('imp94500117'); //가맹점 식별코드
	IMP.request_pay({
	pg: 'inicis', 
	pay_method: pay,  //결제 방법
	merchant_uid: 'merchant_' + new Date().getTime(),
	name: '주문명 : 빈캐시 충전',
	amount: thisismoney, //결제 금액
	buyer_email: 'iamport@siot.do',
	buyer_name: '구매자이름',
	buyer_tel: '010-1234-5678',
	buyer_addr: '서울특별시 강남구 삼성동',
	buyer_postcode: '123-456',
	m_redirect_url: 'https://www.yourdomain.com/payments/complete'
	}, function (rsp) {
	console.log(rsp);
	if (rsp.success) {
	var msg = '결제가 완료되었습니다.';
	msg += '고유ID : ' + rsp.imp_uid;
	msg += '상점 거래ID : ' + rsp.merchant_uid;
	msg += '결제 금액 : ' + rsp.paid_amount;
	msg += '카드 승인번호 : ' + rsp.apply_num;
	$.ajax({
		type:"post",
		url:"<%=request.getContextPath()%>/test.do",
		data:{"amount":rsp.paid_amount,
			  "pay":pay,
			  "userId":userId
		},
		dataType:"json",
		success:function(data){
		}
	})
	} else {
	var msg = '결제에 실패하였습니다.';
	msg += '에러내용 : ' + rsp.error_msg;
	}
	alert(msg);
	});
	});




</script>
      
<%@ include file="/views/common/footer.jsp"%>
        
        




