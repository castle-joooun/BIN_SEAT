<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp"%>
<%@ page import="com.empty.member.model.vo.Member,com.empty.common.listener.SessionCheckListener"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=57f292cf81a06c030ca86c61e79b1b56"></script>
	<script>
	$(document).ready(function(){
		var userId="<%=loginMember.getUserId()%>";
		var cash=0;
		$.ajax({
			url:"<%=request.getContextPath()%>/mypage.do", 
			type:"get",
			dataType:"json",
			data:{
				"userId":userId	
			},
			success:function(data){
				console.log(data['cash']);
				cash=data['cash'];
				$("#cashbox").text(cash+"원");
				$("#addressbox").text(data['address']);
				var bankNumber=data['bankNumber'];
			}
		})
	})
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
				var go = "<%=request.getContextPath()%>/";
				console.log(data);
				$("#storeNamebox").text(data['storeName']).css('width','400px').css('font-weight','400').css('text-align','left');
				$("#storeNumberbox").text(data['storePhone']).css('font-weight','400').css('text-align','left');
				$("#storeTimebox").text(data['storeTime']).css('font-weight','400').css('float','left');
				$("#storecombox").text(data['storeInfo']).css('width','400px').css('font-weight','400').css('text-align','left');
				$("#storeaddressbox").text(data['storeAddress']).css('width','400px').css('font-weight','400').css('text-align','left');
				$("#storebudeabox").text(data['storeFacility']).css('width','400px').css('font-weight','400').css('text-align','left');
				$("#pcimgtag").attr('src',go+data['storeLogo']).css('width','359px').css('height','216px');
			}
		});
	});
	
	</script>
<body>
	<h3 class="mypagemain1">MY PAGE</h3>
	<h3 class="mypagemain2"><a href="<%=request.getContextPath()%>/use/useList">USE</a></h3>
	
	
	<div class="alldiv clearl">
		
		<div class="myinfobox">
			<table>
				<tr>
					<td>
						개인정보
					</td>
					<td>
						<button class="crystal">수정</button>
					</td>
				</tr>
			</table>
		</div>
		
		<div>
			<div class="upzooinfobox">
				<table class="upzoomyinfo1">
					<tr>
						<td>
							아이디
						</td>
						<td colspan='3'>
							<%=loginMember.getUserId() %>
						</td>
					</tr>
					<tr>
						<td>
							이름
						</td>
						<td colspan='3'>
							<%=loginMember.getUserName() %>
						</td>
					</tr>
					<tr>
						<td>
							이메일
						</td>
						<td colspan='3'>
							<%=loginMember.getEmail() %>
						</td>
					</tr>
					<tr>
						<td>
							핸드폰
						</td>
						<td colspan='3'> 
							<%=loginMember.getPhone() %>
						</td>
					</tr>
					<tr>
						<td>
							주소
						</td>
						<td colspan='3' id='addressbox'>
						</td>
					</tr>
					<tr>
						<td>
							성별
						</td>
						<td colspan='3'>
							<%=loginMember.getGender() %>
						</td>
					</tr>
					<tr>
						<td>
							캐시
						</td>
						<td id="cashbox">
						</td>
					</tr>
				</table>
				<div>
							<button class="outmoney">출금</button>
							<button class="enrollgyoja" style='padding-left:14px;padding-right:14px;'>계좌등록</button>
				</div>
			</div>
			
			
			<div class="myinfobox">
				<table>
					<tr>
						<td>
							매장정보
						</td>
						<td>
							<button class="enrollstore" id='crystalstore'>매장수정</button>
							<button class="enrollstore" id='enrollstore'>매장등록</button>
						</td>
					</tr>
				</table>
				
			</div>
			<div class="upzooinfobox1">
				<table class="upzoomyinfo2">
					<tr>
						<td>
							매장이름
						</td>
						<td id='storeNamebox'>
						
						</td>
						<td rowspan='7' class='pcmainimgbox'>
							<img id='pcimgtag' src='<%=request.getContextPath()%>'>
						</td>
					</tr>
					<tr>
						<td>
							매장번호
						</td>
						<td id="storeNumberbox">
						
						</td>
					</tr>
					<tr>
						<td>
							매장 영업시간
						</td>
						<td id='storeTimebox'>
						
						</td>
					</tr>
					<tr>
						<td>
							컴퓨터 사양
						</td>
						<td id='storecombox'>
						
						</td>
					</tr>
					<tr>
						<td>
							매장 주소
						</td>
						<td id='storeaddressbox'>
						
						</td>
					</tr>
					<tr>
						<td>
							부대시설
						</td>
						<td id='storebudeabox'>
						
						</td>
					</tr>
				</table>
			</div>
		</div>
			<button type="button" id='goinsetColRow'>PC방 자리 등록</button>
	
	
	
	
	</div>
	
	
	
	<script>
	$(function(){
		$("#goinsetColRow").click(function(){
			location.href="<%=request.getContextPath()%>/store/insertColRowmp";
		});
	});
	<%-- $(function(){
		$(".mypagemain2").click(function(){ //사용내역으로
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/myUse.do", 
				type:"get",
				dataType:"html",
				success:function(data){
					$(".alldiv").html(data);	
					$(".mypagemain2").css({"font-size":"22px"});
					$(".mypagemain1").css({"font-size":"1.17em"});
				}
			})
		});			
	}); 폐기--%>
	
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
	
	
	$(function(){
		$(".crystal").click(function(){  //개인정보수정으로
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/crystal.do",
				type:"get",
				dataType:"html",
				success:function(data){
					$(".alldiv").html(data);
				}
			});
		});
	});
	
	$(function(){
			$(".enrollgyoja").click(function(){  //계좌등록으로
		if(<%=loginMember.getBankNumber()==null%>){
					$.ajax({
						url:"<%=request.getContextPath()%>/mypage/enrollgyoja.do",
						type:"get",
						dataType:"html",
						success:function(data){
							$(".alldiv").html(data);
						}
					});
		}else{
			alert("이미 계좌가 있습니다.");
			};
		})
	});
	
	$(function(){
		$(".outmoney").click(function(){  //출금으로
				$.ajax({
					url:"<%=request.getContextPath()%>/mypage/outmoney.do",
					type:"get",
					dataType:"html",
					success:function(data){
						$(".alldiv").html(data);
					}
				});
		});
	});
	
	$(function(){
		$("#enrollstore").click(function(){  //매장등록
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/enrollstore.do",
				type:"get",
				dataType:"html",
				success:function(data){
					$(".alldiv").html(data);
				}
			});
		});
	});
	
	
	$(function(){
		$("#crystalstore").click(function(){  //매장수정
			$.ajax({
				url:"<%=request.getContextPath()%>/mypage/crystalstore.do",
				type:"get",
				dataType:"html",
				success:function(data){
					$(".alldiv").html(data);
				}
			});
		});
	});
	
	</script>
</body>
</html>