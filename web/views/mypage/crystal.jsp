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
		$.ajax({
			url:"<%=request.getContextPath()%>/mypage/pcdb", 
			type:"get",
			dataType:"json",
			data:{
				"userId":userId	
			},
			success:function(data){
				$("#crystalbank").attr('value',data['bank']);
				console.log("dd")
			}
		});
	});
</script>	
	<div class="myinfobox">
			<table>
				<tr>
					<td>
						개인정보 수정
					</td>
				</tr>
			</table>
		</div>
	<div class="upzooinfobox">
				<table class="crystalinfobox">
					<tr>
						<td>
							아이디
						</td>
						<td>
							<%=loginMember.getUserId() %>
						</td>
					</tr>
					<tr>
						<td>
							비밀번호
						</td>
						<td id='crystalpwbox'>
							<input type='password' id='crystalpw1' placeholder='변경할 비밀번호' value=''>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<input type='password' id='crystalpw2' placeholder='변경할 비밀번호 확인'><button type='button' onclick='crystalpwcheck();'>비밀번호 확인</button><p id="crystalpwok" style='display:inline'></p>
						</td>
					</tr>
					<tr>
						<td>
							이름
						</td>
						<td>
							<%=loginMember.getUserName() %>
						</td>
					</tr>
					<tr>
						<td>
							이메일
						</td>
						<td>
							<%=loginMember.getEmail() %>
						</td>
					</tr>
					<tr>
						<td>
							핸드폰
						</td>
						<td> 
							<%=loginMember.getPhone() %>
						</td>
					</tr>
					<tr>
						<td>
							주소
						</td>
						<td>
							<input type='text' value='<%=loginMember.getAddress() %>' id='crystaladdress'>
						</td>
					</tr>
					<tr>
						<td>
							성별
						</td>
						<td>
							<%=loginMember.getGender() %>
						</td>
					</tr>
					<tr>
						<td>
							은행
						</td>
						<td>
							<%if(loginMember.getBank()!=null){ %>
							<input type='text'  id='crystalbank' value="<%=loginMember.getBank()%>">
							<%}else{ %>
							<input type='text'  id='crystalbank' value="">
							<%} %>
						</td>
					</tr>
					<tr>
						<td>
							계좌번호
						</td>
						<td>
							<%if(loginMember.getBank()!=null){ %>
							<input type='text'  id='crystalgyoja' value="<%=loginMember.getBankNumber()%>"><p id="crystalgyojabox" style='display:inline'></p>
							<%}else{ %>
							<input type='text'  id='crystalgyoja' value=""><p id="crystalgyojabox" style='display:inline'></p>
							<%} %>
						</td>
					</tr>
					<tr>
						<td>
							예금주
						</td>
						<td>
							<%if(loginMember.getBank()!=null){ %>
							<input type='text'  id='crystalbm' value="<%=loginMember.getBankMaster()%>">
							<%}else{ %>
							<input type='text'  id='crystalbm' value="">
							<%} %>
						</td>
					</tr>
					<tr class='chobtnbox'>
						<td>
							<button class="crystalcom">수정</button>
						</td>
					</tr>
				</table>
			</div>
	<script>
	$(function(){
		$(".crystalcom").click(function(){
			var userId = "<%=loginMember.getUserId()%>";
			var password = $("#crystalpw1").val();
			var address = $("#crystaladdress").val();
			var bank = $("#crystalbank").val();
			var bankNumber = $("#crystalgyoja").val();
			var bankMaster = $("#crystalbm").val();
			if(password==""){
				password="<%=loginMember.getPassword()%>";
			}
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/crystalcom.do",
				type:"post",
				data:{
					"userId":userId,
					"password":password,
					"address":address,
					"bank":bank,
					"bankNumber":bankNumber,
					"bankMaster":bankMaster,
				},
				dataType:"json",
				success:function(data){
					alert(data['msg']);
					location.href="<%=request.getContextPath()%>/store/mypage";
				}
			})
		})
	})
	
	
	$(function(){
		$(".byevin").click(function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/byevin.do",
				type:"post",
				data:{"userId":<%=loginMember.getUserId()%>},
				dataType:"json",
				success:function(data){
					$(".alldiv").html(data);
				}
			})
		})
	})
	
			function crystalpwcheck() {
				var crystalpw1 = document.getElementById("crystalpw1").value;
				var crystalpw2 = document.getElementById("crystalpw2").value;
				var crystalpwok = document.getElementById("crystalpwok");
				if (crystalpw1 != '' && crystalpw2 != '') {
					if (crystalpw1 == crystalpw2) {
						crystalpwok.innerHTML = "비밀번호가 일치합니다.";
						crystalpwok.style.color = "blue";
						return true;
					} else {
						crystalpwok.innerHTML = "※비밀번호가 일치하지 않습니다.";
						crystalpwok.style.color = "red";
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
			
			$("#crystalgyoja").keyup(function(){
				var uId = document.getElementById("crystalgyoja").value;
				var crystalgyojabox = document.getElementById("crystalgyojabox");
				var condition = /^[0-9]*$/;
				if (condition.test(uId)) {
					crystalgyojabox.innerHTML = "좋습니다.";
					crystalgyojabox.style.color = "blue";
					return true;
				} else {
					crystalgyojabox.innerHTML = "숫자만 입력해주세요.";
					crystalgyojabox.style.color = "red";
					return false;
				}
			})
			
	</script>
