<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,com.empty.member.model.vo.InputMoneyDB" %>	
<%@ include file="/views/common/header.jsp"%>
<%
	List<InputMoneyDB> list=(List)request.getAttribute("list");
%>
	<h3 class="mypagemain3"><a href="<%=request.getContextPath()%>/store/mypage">MY PAGE</a></h3>
	<h3 class="mypagemain2"><a href="<%=request.getContextPath()%>/use/imList">IN</a></h3>
	<h3 class="mypagemain4"><a href="<%=request.getContextPath()%>/use/useList">OUT</a></h3>
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
					<td>
						입금후 잔액
					</td>
				</tr>
			<tr>
		 		<%if(list.size() !=0){ %>
		<%for(InputMoneyDB imdb : list) {%>
				<tr>
					<td>
						<%=imdb.getIpDate() %>
					</td>
					<td>
						<%=imdb.getUserId() %>
					</td>
					<td>
						<%=imdb.getIpMoney() %>
					</td>
					<td>
						<%=imdb.getAfterIm() %>
					</td>
				</tr>
			<%} 
			}else{%>
			<tr>
				<td></td>
				<td colspan='2' style="text-align: center;font-weight:300;">입금 내역이 없습니다.</td>
				<td></td>				

			</tr>
			<%} %>
			</table>
			<div id='inmoneyListpagebar' style='position:relative;left:45%;'>
					<%=request.getAttribute("pageBar2") %>
				</div>
		</div>
		</div>
