<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.empty.member.model.vo.outMoneyDB" %>	
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base2.css?ver=3.6" type="text/css">
<%@ include file="/views/common/header.jsp"%>
<%
	List<outMoneyDB> list=(List)request.getAttribute("list");
%>
	<h3 class="mypagemain1">MY PAGE</h3>
	<h3 class="mypagemain2"><a href="<%=request.getContextPath()%>/use/useList">USE</a></h3>
		<div class="alldiv">
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
				<tr>
					<td>
						출금날짜
					</td>
					<td>
						출금계좌
					</td>
					<td>
						출금금액
					</td>
					<td>
						출금후 잔액
					</td>
				</tr>
		<%for(outMoneyDB omdb : list) {%>
				<tr>
					<td>
						<%=omdb.getOmDate() %>
					</td>
					<td>
						<%=omdb.getOmNumber() %>
					</td>
					<td>
						<%=omdb.getOm() %>
					</td>
					<td>
						<%=omdb.getAfterOm() %>
					</td>
				</tr>
			<%} %>
			</table>
				<div id='useListpagebar'>
					<%=request.getAttribute("pageBar") %>
				</div>	
			</div>
		</div>
		<script>
		$(function(){
			var userId="<%=loginMember.getUserId()%>";
			$(".mypagemain1").click(function(){  //내정보로
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
			});			
		});
		</script>
