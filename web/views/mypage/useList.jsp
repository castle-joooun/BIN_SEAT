<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.empty.member.model.vo.outMoneyDB,com.empty.member.model.vo.InputMoneyDB" %>	
<%@ include file="/views/common/header.jsp"%>
<%
	List<outMoneyDB> list=(List)request.getAttribute("list");
%>
	<h3 class="mypagemain3"><a href="<%=request.getContextPath()%>/store/mypage">MY PAGE</a></h3>
	<h3 class="mypagemain2"><a href="<%=request.getContextPath()%>/use/imList">IN</a></h3>
	<h3 class="mypagemain4"><a href="<%=request.getContextPath()%>/use/useList">OUT</a></h3>
		<div class="alldiv">
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
				<tr class="myuseListbaby">
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
		<%if(list.size() !=0){ %>
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
			<%} 
			}else{%>
			<tr>
				<td></td>
				<td colspan='2' style="text-align: center">출금 내역이 없습니다.</td>
				<td></td>				

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
