<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.empty.member.model.vo.Member,com.empty.common.listener.SessionCheckListener,java.util.List"%>
<%
	Member loginMember = (Member) session.getAttribute("loginMember");
%>
	<script>
	$(document).ready(function(){
		var userId="<%=loginMember.getUserId()%>";
		$.ajax({
			url:"<%=request.getContextPath()%>/use/use.do", 
			type:"get",
			dataType:"json",
			data:{
				"userId":userId	,
				"cPage":1
			},
			success:function(data){
				console.log(data);
				console.log(data['list'][0]['omDate']);
				console.log(data['list'].length);
				const tr=$("<tr>");
				const tr3=$("<tr>");
				tr3.append($("<td>").html("출금날짜").css('font-size','18px').css('font-weight','bold'));
				tr3.append($("<td>").html("출금 계좌번호").css('font-size','18px').css('font-weight','bold'));
				tr3.append($("<td>").html("금액").css('font-size','18px').css('font-weight','bold'));
				tr3.append($("<td>").html("출금후 금액").css('font-size','18px').css('font-weight','bold'));
				tr.append(tr3);
				for(let i=0;i<data['list'].length;i++){
					const tr2=$("<tr>");
						tr2.append($("<td>").html(data['list'][i]['omDate']));
						tr2.append($("<td>").html(data['list'][i]['omNumber']));
						tr2.append($("<td>").html(data['list'][i]['om']));
						tr2.append($("<td>").html(data['list'][i]['afterOm']));
						tr.append(tr2);
				}
				$(".myuseList table").html(tr);
				$("#useListpagebar").html(data['pageBar']);
			}
			
		})
	})
	</script>
		<div class="useList">
			<table>
				<tr>
					<td>
						입금내역
					</td>
				</tr>
			</table>
		</div>
		
		<div class="inmoneyList">
			<table>
				<tr>
					<td>
						입금날짜
					</td>
					<td>
						입금자
					</td>
					<td>
						금액
					</td>
				</tr>
			</table>
		</div>
		<div class="useList">
			<table>
				<tr>
					<td>
						출금내역
					</td>
				</tr>
			</table>
		</div>
		
		<div class="myuseList">
			<table>
			</table>
		</div>
		<div id='useListpagebar'>
		
		</div>