<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.empty.member.model.vo.outMoneyDB" %>	
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base2.css?ver=3.6" type="text/css">
<%@ include file="/views/common/header.jsp"%>
<%@ page
	import="com.empty.member.model.vo.Member,com.empty.common.listener.SessionCheckListener"%>

<center>
	<div>
		<h2>매장 좌석의 총 자릿수를 입력해주세요.</h2>
		<p>매장의 가로 좌석을 입력해주세요 : 
			<input type="text" name="col" placeholder="복도포함 입력해주세요.">
		</p>
		<p>매장의 세로 좌석을 입력해주세요 :
			<input type="text" name="row" placeholder="복도포함 입력해주세요.">
		</p>
		<input type="button" id="seatBtn" value="자리생성">
	</div>
	<div id="seat">
	</div>
</center>


<script>
	$("#seatBtn").click(function() {
		
		$("#seat").prepend($("<h2>").html("매장의 자리를 분류해주세요."));
		
		var col = $("input[name='col']").val();
		var row = $("input[name='row']").val();
		console.log("col : " + col + ", row : " + row);
		
		for(let i=0; i<row; i++) {
			let tr = $("<tr>");
			for(let n=0; n<col; n++) {
				let td = $("<td>");
				let select = $("<select>").addClass("select");
				let option1 = $("<option>");
				let option2 = $("<option>").val(1);
				let option3 = $("<option>").val(0);
				option1.html("선택하기");
				option2.html("자리");
				option3.html("복도");
				select.append(option1);
				select.append(option2);
				select.append(option3);
				td.append(select);
				tr.append(td);
			}
			$("#seat").append(tr);
		}
		
		$("#seat").append($("<button>").attr({"id":"createBtn"}).html("생성완료"));
		
		$(".select").change(function() {
			var num = $(this).val();
			
			
			switch(num) {
            case "1": 
            	$(this).parent().css({"border":"solid 1px black", "backgroundColor":"#5e64b1"}); 
            	$(this).css({"color":"white", "backgroundColor":"#5e64b1", "border":"solid 1px #5e64b1"});
            	$(this).addClass("check");
            	break;
            case "0": 
            	$(this).parent().css({"border":"solid 1px lightgray", "backgroundColor":"white"}); 
            	$(this).css({"color":"lightgray", "backgroundColor":"white", "border":"solid 1px lightgray"});
            	$(this).addClass("check");
            	break;
            default:
            	$(this).removeClass("check");
        	}
		});
		
		var totalSeat = "";
		var totalNum = "";
		let totalCount = row * col;
		var seatNums = 1;
		
		$("#createBtn").click(function() {
 			if(totalCount == $(".check").length) {
				$(".check").each(function() {
					
					if(seatNums==totalCount) {
						totalSeat += $(this).val();
					} else {
						totalSeat += $(this).val() + ",";
					}
					seatNums++;	
				})
				$.ajax({
					url:"<%=request.getContextPath()%>/createSeat",
					data:{"col":col, "row":row, "storeId":"<%=loginMember.getUserId()%>", "seatCheck":totalSeat},
					type:"post",
					success:function(data) {
						alert("자리가 등록되었습니다.");
						location.href("<%=request.getContextPath()%>/");
					}
				})
			} else {
				alert("자리를 전부 분류해주세요.");
			} 
		})
	})
	
</script>

<style>
#seat td {
	position: relative;
	width:100px;
	height:100px;
	border: solid 1px black;
	text-align: center;
	padding-top:40px;
}
</style>
</body>
</html>