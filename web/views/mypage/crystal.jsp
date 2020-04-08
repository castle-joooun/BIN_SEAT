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
						<td>
							<input type='text' placeholder='변경할 비밀번호'>
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
							<input type='text' placeholder='<%=loginMember.getAddress() %>'>
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
							<input type='text' placeholder='<%=loginMember.getBank() %>'>
						</td>
					</tr>
					<tr>
						<td>
							계좌번호
						</td>
						<td>
							<input type='text' placeholder='<%=loginMember.getBankNumber() %>'>
						</td>
					</tr>
					<tr>
						<td>
							예금주
						</td>
						<td>
							<input type='text' placeholder='<%=loginMember.getBankMaster() %>'>
						</td>
					</tr>
					<tr class='chobtnbox'>
						<td>
							<button class="crystalcom">수정</button>
							<button class="byevin">탈퇴</button>
						</td>
					</tr>
				</table>
			</div>
	<script>
	$(function(){
		$(".crystalcom").click(function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/crystalcom.do",
				type:"post",
				/* data:{""}, */
				dataType:"json",
				success:function(data){
					$(".alldiv").html(data);
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
	</script>
