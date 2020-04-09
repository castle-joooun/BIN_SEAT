<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page
	import="com.empty.member.model.vo.Member,com.empty.common.listener.SessionCheckListener"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
<script>
	$(document).ready(function(){
		var userId="<%=loginMember.getUserId()%>";
		var cash=0;
		$.ajax({
			url:"<%=request.getContextPath()%>/mypage.do", 
			type:"get",
			dataType:"json",
			data:{
				"userId":userId	
			},
			success:function(data){
				console.log(data['cash']);
				cash=data['cash'];
				$(".cashcomp1").text("출금가능 잔액");
				$(".cashcomp2").text(cash+"원").css('font-weight','300');
				$("#bankinbox").text(data['bank']+"은행").css('font-weight','300');
				$("#bankinnum").text(data['bankNumber']).css('font-weight','300');
				$("#bankinma").text(data['bankMaster']).css('font-weight','300');
			}
		})
	})
	</script>
		<div class="myinfobox">
			<table>
				<tr>
					<td>
						출금
					</td>
				</tr>
			</table>
		</div>
		<div class="outMoneyBox">
				<table class="cashouputbox">
					<tr>
						<td class="cashcomp1">
						</td>
						<td class="cashcomp2" style="position:relative;left:-165px;">
						</td>
					</tr>
					<tr>
						<td>
							출금하실 금액 <input type="text" id="choiceOutMoney" placeholder='최소금액 1000원'></br><p id='choiceOutMoneyview' style='display:inline'></p>
						</td>
					</tr>
					<tr>
						<td>
							은행
						</td>
						<td style="position:relative;left:-165px;" id="bankinbox">
						</td>
					</tr>
					<tr>
						<td>
							계좌번호
						</td>
						<td style="position:relative;left:-165px;" id="bankinnum">
						</td>
					</tr>
					<tr>
						<td>
							예금주
						</td>
						<td style="position:relative;left:-165px;" id="bankinma">
						</td>
					</tr>
				</table>
				<button id="goOutMoneySV">출금</button>
		</div>
		
		
		<script>
		$(function(){
			$("#goOutMoneySV").click(function(){
				var choiceOutMoney = $("#choiceOutMoney").val();
				var userId="<%=loginMember.getUserId()%>";
				$.ajax({
					url:"<%=request.getContextPath()%>/cash/goOutMoneySV.do",
					type:"post",
					data:{
						  "money":choiceOutMoney,
						  "userId":userId
					},
					dataType:"json",
					success:function(data){
						alert(data['msg']);
						$.ajax({
							url:"<%=request.getContextPath()%>/mypage/myPageList",
							type:"get",
							dataType:"html",
							success:function(data){
								$(".alldiv").html(data);		
								$(".mypagemain1").css({"font-size":"22px"});
								$(".mypagemain2").css({"font-size":"1.17em"});
							}
						})
					},error:function(data){
						
					}
				})
			})
		})
		
		$("#choiceOutMoney").keyup(function(){
				var uId = document.getElementById("choiceOutMoney").value;
				var choiceOutMoneyview = document.getElementById("choiceOutMoneyview");
				var condition = /^[0-9]*$/;
				if (condition.test(uId)) {
					choiceOutMoneyview.innerHTML = "좋습니다.";
					choiceOutMoneyview.style.color = "blue";
					return true;
				} else {
					choiceOutMoneyview.innerHTML = "숫자만 입력해주세요.";
					choiceOutMoneyview.style.color = "red";
					return false;
				}
			})
		</script>