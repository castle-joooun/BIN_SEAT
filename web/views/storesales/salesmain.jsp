<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/storesales/salesmain.css?ver=2" type="text/css">
<% 
	String dateYoil = (String)request.getAttribute("dateYoil");
	String date = (String)request.getAttribute("date");
	String yoil = (String)request.getAttribute("yoil");
	String minusDate = (String)request.getAttribute("minusDate");
	String plusDate = (String)request.getAttribute("plusDate");
%>

<div class="subMenuContainer">

	<div id="storeSubMenu">
		<ul>
			<li><a href="<%=request.getContextPath()%>/store/salesView"><span class="text-item">일매출 보기</span></a></li>
			<li><a href="<%=request.getContextPath()%>/store/monthSalesView"><span>월매출보기</span></a></li>
			
		</ul>
	</div>
	

</div>
<div id="contentContainer">
	<div id="dayContainer">
		<div>
			<form action="<%=request.getContextPath()%>/store/salesView">
				<input type="hidden" name ="date" value="<%=minusDate%>">
				<button><img src="<%=request.getContextPath()%>/image/left.png"></button>
			</form>
		</div>
		<div id="dateStyle"><%=dateYoil %></div>
		<div>
			<form action="<%=request.getContextPath()%>/store/salesView">
				<input type="hidden" name ="date" value="<%=plusDate%>">
				<button><img src="<%=request.getContextPath()%>/image/right.png"></button>
			</form>
		</div>
	</div>
	<div class="btn-container">
	
	</div>
	<div id="tableContainer">
		
	</div>
</div>
<script>
	$(function(){
		var date = '<%=date%>';
		console.log(date);
		requestSales(date);
	})
	function requestSales(date){
		$.ajax({
			url:"<%=request.getContextPath()%>/store/dailySales/ajax",
			dataType:"json",
			type:"post",
			data:{"date":date,"storeId":'<%=loginMember.getUserId()%>'},
			success:function(data){
				console.log(data);
				if(data!=null){
					const table = $("<table>");
					table.append($("<tr>").append($("<td colspan='2'>").html(data['storeName'])));
					table.append($("<tr>").append($("<td>").html("고객수")).append($("<td>").html(data['customer']+"명")));
					table.append($("<tr>").append($("<td>").html("공급가액")).append($("<td>").html(data['netProfit']+"원")));
					table.append($("<tr>").append($("<td>").html("부가세")).append($("<td>").html(data['tax']+"원")));
					table.append($("<tr>").append($("<td>").html("총매출")).append($("<td>").html(data['totalProfit']+"원")));
					table.append($("<tr>").append($("<td>").html("객단가")).append($("<td>").html(data['netProfit']/data['customer']+"원")));
					
					$("#tableContainer").append("<form action='<%=request.getContextPath()%>/store/enrollDailySales' method='post'>"+
							"<button>마감하기</button>"+
							"<input type='hidden' name='storeId' value='"+data['storeId']+"'>"+
							"<input type='hidden' name='customer' value='"+data['customer']+"'>"+
							"<input type='hidden' name='netProfit' value='"+data['netProfit']+"'>"+
							"<input type='hidden' name='tax' value='"+data['tax']+"'>"+
							"<input type='hidden' name='totalProfit' value='"+data['totalProfit']+"'>"+
							"<input type='hidden' name='storeName' value='"+data['storeName']+"'>"+
							"<input type='hidden' name='date' value='"+'<%=date%>'+"'>"+
							"<input type='hidden' name='yoil' value='"+'<%=yoil%>'+"'>"+
							"</form>");
					$("#tableContainer").append(table);
					
				}else{
					$("#tableContainer").append("검색된 정보가 없습니다.");
				}
			}
			
		})
	}
	
</script>


</body>


</html>