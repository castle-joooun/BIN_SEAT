<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page
	import="com.empty.member.model.vo.Member,com.empty.common.listener.SessionCheckListener"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
<div class="myinfobox">
			<table>
				<tr>
					<td>
						계좌등록
					</td>
				</tr>
			</table>
		</div>
		
			<div class="upzooinfobox">
				<table class="enrollbox">
					<tr>
						<td>
							계좌번호
						</td>
						<td>
							<input type="text" placeholder='-는 빼고 입력해주세요.' id="gyojanumber">
						</td>
					</tr>
					<tr>
						<td>
							은행
						</td>
						<td>
							<label>국민<input type='radio' name='bank' value='국민'></label>
							<label>하나<input type='radio' name='bank' value='하나'></label>
							<label>기업<input type='radio' name='bank' value='기업'></label>
							<label>농협<input type='radio' name='bank' value='농협'></label>
							<label>신한<input type='radio' name='bank' value='신한'></label>
						</td>
					</tr>
					<tr>
						<td>
							예금주
						</td>
						<td>
							<input type='text' id="bankMaster">
						</td>
					</tr>
				</table>
				<button id="enrollgyojagirit">등록</button>
			</div>
			
			<script>
			$(function(){
				$("#enrollgyojagirit").click(function(){ 
				var userId="<%=loginMember.getUserId()%>";
				var gyojanumber=$("#gyojanumber").val();
				var bankMaster=$("#bankMaster").val();
				var bank=$('input[name="bank"]:checked').val();
					$.ajax({
						url:"<%=request.getContextPath()%>/mypage/enrollgyojaGoDB",
						type:"get",
						dataType:"json",
						data:{
							"userId":userId,
							"gyojanumber":gyojanumber,
							"bankMaster":bankMaster,
							"bank":bank,
						},
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
						}
					})
				});			
			});
			
			$("#gyojanumber").bind("keyup", function(event) {
			    var regNumber = /^[0-9]*$/;
			    var temp = $("#gyojanumber").val();
			    if(!regNumber.test(temp))
			    {
			        alert('숫자만 입력하세요');
			        $("#gyojanumber").val(temp.replace(/[^0-9]/g,""));
			    }
			});
			</script>