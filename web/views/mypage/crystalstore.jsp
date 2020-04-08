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
				console.log(data);
				var restoreNAme = $("<input>").attr('type','text').attr('id','storeName').attr('value',data['storeName']);
				var restoreNumber = $("<input>").attr('type','text').attr('id','storePhone').attr('value',data['storePhone']);
				var restoreTime = $("<input>").attr('type','text').attr('id','storeTime').attr('value',data['storeTime']);
				var restorecom = $("<input>").attr('type','text').attr('id','storeInfo').attr('value',data['storeInfo']);
				var restoreaddress = $("<input>").attr('type','text').attr('id','storeAddress').attr('value',data['storeAddress']);
				var restorebudea = $("<input>").attr('type','checkbox').attr('id','storeFacility').attr('checked',data['storeFacility']);
				var restorestorePrice = $("<input>").attr('type','text').attr('id','storePrice').attr('value',data['storePrice']);
				$("#1").attr('type','time').attr('id','storeTimes').attr('value',data['storeTimes']);
				$("#2").attr('type','time').attr('id','storeTimee').attr('value',data['storeTimee']);
				$('#crystalstoreNamebox').html(restoreNAme);
				$('#crystalstoreNumberbox').html(restoreNumber);
				$('#crystalstoreInfobox').html(restorecom);
				$('#crystalstorePricebox').html(restorestorePrice);
			}
		});
	});
</script>
		<div class="myinfobox">
			<table>
				<tr>
					<td>
						매장정보수정
					</td>
				</tr>
			</table>
		</div>
		
			<div class="upzooinfobox">
				<table class="enrollbox">
					<tr>
						<td>
							매장이름
						</td>
						<td id='crystalstoreNamebox'>
						
						</td>
					</tr>
					<tr>
						<td>
							매장 전화번호
						</td>
						<td id='crystalstoreNumberbox'>

						</td>
					</tr>
					<tr>
						<td>
							영업시간
						</td>
						<td id='crystalstoreTimebox'>
							<input id='1'> ~ <input id='2'>
						</td>
					</tr>
					<tr>
						<td>
							매장정보
						</td>
						<td id='crystalstoreInfobox'> 
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<p>ex)CPU - AMD라이젠7 3700X 3.6GHz, 메인보드 - MSI MPG X570 게이밍 플러스</p>
						</td>
					</tr>
					<tr>
						<td>
							시간당 금액
						</td>
						<td id='crystalstorePricebox'>
						</td>
					</tr>
				</table>
				<button id="storecrystalbtn">수정</button>
			</div>
			
			<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>

		
		
	
	   
	
			$(function(){
			
				
				$("#storecrystalbtn").click(function(){
					var storeName=$("#storeName").val();
					var storePhone=$("#storePhone").val();
					var storeTimestart=$("#storeTimes").val();
					var storeTimeclose=$("#storeTimee").val();
					var storeInfo=$("#storeInfo").val();
					var storePrice=$("#storePrice").val();
					var storeFacirity=[];
					var userId="<%=loginMember.getUserId()%>";
					var form=$("#frm").serialize();
					var fileupload=$("#fileupload").val();
					console.log(storeName);
					
					$.ajax({
						url:"<%=request.getContextPath()%>/enroll/crystalstore.do",
						data:{
							  "userId":userId,
							  "storeName":storeName,
							  "storePhone":storePhone,
							  "storeTimestart":storeTimestart,
							  "storeTimeclose":storeTimeclose,
							  "storeInfo":storeInfo,
							  "storePrice":storePrice,
						},
						traditional : true,
						dataType:"json",
						type:"post",
						success:function(data){
							alert("수정 성공");
						},
					})
				});
			})
			
			
			
			</script>