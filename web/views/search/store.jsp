<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="storeBaseTop.jsp"%>
<%@ page import="com.empty.search.model.vo.Store, java.util.List"%>
<%@ page
	import="com.empty.search.model.vo.StoreSeat, com.empty.member.model.vo.Member"%>

<link rel="stylesheet" href="css/store.css?ver=0" type="text/css">

<img src="image/back.png" alt="" id="back" width="20px">
<img src="image/next.png" alt="" id="next" width="20px">

<%
	Store s = (Store) request.getAttribute("store");
	System.out.println("store.jsp : " + s);
	String[] info = s.getStoreInfo().split(", ");
	List<String> imgs = (List) request.getAttribute("imgs");
	StoreSeat ss = (StoreSeat) request.getAttribute("storeSeat");
	String[] seatCheck = ss.getSeatNum().split(",");
	String[] useCheck = ss.getSeatCheck().split(",");
	int seatNum = 1;
	int seatNum2 = 1;
%>


<div id="imgBox">
	<center>
		<li id="imgSlide">
			<%
				for (String str : imgs) {
			%> <img src="<%=str%>" alt="" class="interior"> <%
 	}
 %>
		</li>
	</center>
</div>

<div id="store">
	<div id="storeInfo">

		<center>
			<div id="storeTitleBox">
				<p id="storeTitle"><%=s.getStoreName()%></p>
				<img src="<%=request.getAttribute("url")%>" alt="" id="favorite">
			</div>
		</center>
		<!-- <img > -->
		<div id="clickNone">
			<div id="storeInfoBasic">
				<div id="storePhone">
					<span class="storeInfoBasicMajor">전화번호 : </span>
					<sapn class="storeInfoBasicMinor"><%=s.getStorePhone()%> </sapn>
				</div>
				<div id="storeTime">
					<span class="storeInfoBasicMajor">운영시간 : </span>
					<sapn class="storeInfoBasicMinor"><%=s.getStoreTime()%> </sapn>
				</div>
				<div id="storeAdd">
					<span class="storeInfoBasicMajor">부대시설 : </span>
					<sapn class="storeInfoBasicMinor"><%=s.getStoreFacility()%>
					</sapn>
				</div>
				<div id="storePc">
					<span class="storeInfoBasicMajor">PC정보 : </span>
					<sapn class="storeInfoBasicMinor"> <%
 	for (String str : info) {
 %> <%=str%><br>
					<%
						}
					%> </sapn>
				</div>
			</div>
			<div id="storeInfoMap">
				<span class="storeInfoBasicMajor">주소 : </span> <span
					class="storeInfoBasicMinor"><%=s.getStoreAddress()%></span>
				<!-- 주소API 넣기 -->
				<div id="map" style="width: 480px; height: 300px;"></div>
			</div>
		</div>


		<button id="viewSeatBtn">빈시트 CHECK!</button>
	</div>

	<div id="viewSeat">
		<p id="seatInfoText">
			<!-- 원래는 값 받아와서 넣기 -->
			빈시트 : <span id="countEmptySeat"></span> 꽉시트 : <span
				id="countFullSeat"></span>
		</p>
		<center>
			<table>
				<%
					for (int i = 1; i <= ss.getRow(); i++) {
				%>
				<tr>
					<%
						for (int n = 1; n <= ss.getCol(); n++) {
								if (seatCheck[(seatNum - 1)].equals("0")) {
					%>
					<td></td>
					<%
						} else {
					%>
					<%
						if (useCheck[seatNum - 1].equals("0")) {
					%>
					<td class="emptySeat seat">
						<p>사용가능</p>
						<button type="button" class="reservationBtn"
							value="<%=seatCheck[(seatNum - 1)]%>">예약하기</button>
					</td>
					<%
						} else if (useCheck[seatNum - 1].equals("1")) {
					%>
					<td class="fullSeat seat"></td>
					<%
						}
					%>
					<%
						}
								seatNum++;
							}
					%>
				</tr>
				<%
					}
				%>
			</table>

		</center>
	</div>

	<div id="comment">
		<table>
			<tr>
				<th>댓글</th>
				<td><button id="refresh">새로고침</button></td>
			</tr>
			<tr>
				<td><textarea id="commentInput"></textarea></td>
				<td><button id="commentBtn">등록</button></td>
			</tr>
		</table>
	</div>
	<%
		if (loginMember != null) {
	%>
	<div id="reservation">
		<p id="storeReInfo">
			매장 : <strong><%=s.getStoreName()%></strong>&nbsp;&nbsp;&nbsp;&nbsp;
			선택자리 : <strong><span id="selectedSeatText"></span></strong>
		</p>
		<p id="userInfo">
			ID : <strong><%=loginMember.getUserId()%></strong>&nbsp;&nbsp;&nbsp;&nbsp;
			빈캐시 : <strong><span id="userCash"><%=loginMember.getCash()%></span></strong>원
		</p>
		<div id="underLine"></div>

		<center>
			<div id="reView">
				<img id="stepImg" src="image/step1.png" alt="">
			</div>

			<div id="reStep1" class="reStep">
				<div class="reStepTitle">
					<span>이용시간 선택</span> <span id="reStepMini">(1시간당 <%=s.getStorePrice()%>원)
					</span>
					<!-- 스토어프라이스로 넣어주기 -->
				</div>
				<div class="reStepContent">
					<label> <input type="radio" name="time" value="1">1시간
					</label> <label> <input type="radio" name="time" value="2">2시간
					</label> <label> <input type="radio" name="time" value="3">3시간
					</label> <label> <input type="radio" name="time" id="ectTime">
						<input id="ectNum" type="text"
						style="padding: 0; margin: 0; display: inline-block; width: 30px; text-align: center;">시간
					</label>
				</div>
			</div>
			<div class="reStep" id="reStep2">
				<div class="reStepTitle">예약내역확인</div>
				<div
					style="position: realtive; display: inline-block; margin-top: 15px">
					<div
						style="position: relative; display: inline-block; float: left; text-align: right;">
						<p>매장 :&nbsp;</p>
						<p>매장운영시간 :&nbsp;</p>
						<p>현재시간 : &nbsp;
						<p>
						<p>이용시간 : &nbsp;</p>
						<p>이용금액 : &nbsp;</p>
						<p>결제 후 빈캐시 : &nbsp;</p>
					</div>
					<div
						style="position: relative; display: inline-block; float: left; text-align: left;">
						<p>
							<strong><%=s.getStoreName()%></strong>
						</p>
						<p>
							<strong><%=s.getStoreTime()%></strong>
						</p>
						<p>
							<strong><span id="clock"></span></strong>
						</p>
						<p>
							<strong><span id="willUseTime"></span> 시간</strong>
						</p>
						<p>
							<strong><span id="willPayMoney"></strong> 원
						</p>
						<!-- 스토어프라이스로 넣어주기 -->
						<p>
							<strong><span id="afterMoney"></span> 빈캐시</strong>
						</p>
					</div>
					<div
						style="position: relative; display: inline-block; float: left; text-align: right;">
						<p>
							매장 주소 : <strong><%=s.getStoreAddress()%></strong>
						</p>
						<div id="map2"
							style="position: relative; width: 480px; height: 150px; left: 28px"></div>
					</div>
				</div>

				<div id="viewSeat2">
					<p id="reTitleSeat">선택하신 자리</p>
					<table>
						<%
							for (int i = 1; i <= ss.getRow(); i++) {
						%>
						<tr>
							<%
								for (int n = 1; n <= ss.getCol(); n++) {
							%>
							<%
								if (!seatCheck[seatNum2 - 1].equals("0")) {
							%>
							<td class="reSeat" value="<%=seatNum2%>"></td>
							<%
								} else {
							%>
							<td></td>
							<%
								}
							%>
							<%
								seatNum2++;
							%>
							<%
								}
							%>
						</tr>
						<%
							}
						%>
					</table>
				</div>
			</div>
		</center>

		<div id="reBtns">
			<button id="reCan">취소</button>
			<button id="reOk">다음</button>
		</div>
	</div>
	<%
		}
	%>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1f47dde95b7968538cbb3ad2e6003356&libraries=services"></script>
	<script>
	
		let selectPcRow = 0;
		let selectPcCol = 0;
	
	  //즐겨찾기
	    $("#favorite").click(function () {
	    	
	    	
	    	if(<%=loginMember != null%>) {
	    		
		    	var check;
		    	
		        if($("#favorite").attr("src") == "image/favorite-empty.png"){
		        	check = true;
		        } else {
		        	check = false;
		        }
		        
		        $.ajax({
		        	url:"<%=request.getContextPath()%>/favorite",
		        	type:"post",
		        	data:{"check":check, "userId":"<%=loginMember != null ? loginMember.getUserId() : ""%>", "storeId":"<%=s.getStoreId()%>"},
		        	success:function(data) {
		        		console.log(data);
		        		if(data==6) {
		        			alert("!즐겨찾기 최대개수(6개) 도달! : 즐겨찾기를 삭제 후 추가해주세요.");
		        		} else if(data<6) {
		        			if($("#favorite").attr("src")==("image/favorite-use.png")) {
		        				$("#favorite").attr("src","image/favorite-empty.png");	     
		        			} else {
		        				$("#favorite").attr("src","image/favorite-use.png");	        				
		        			}
		        		}
		        	}
		        })
	    	} else {
	    		alert("로그인을 해주세요!");
	    	}
    })
    
    
    // 지도 API
    $(function() {
    	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };  

	    // 지도를 생성합니다    
	    var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	    // 주소-좌표 변환 객체를 생성합니다
	    var geocoder = new kakao.maps.services.Geocoder();
	
	    // 주소로 좌표를 검색합니다
	    geocoder.addressSearch('<%=s.getStoreAddress()%>', function(result, status) {
	
	        // 정상적으로 검색이 완료됐으면 
	         if (status === kakao.maps.services.Status.OK) {
	
	            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	            // 결과값으로 받은 위치를 마커로 표시합니다
	            var marker = new kakao.maps.Marker({
	                map: map,
	                position: coords
	            });
	
	            // 인포윈도우로 장소에 대한 설명을 표시합니다
	            var infowindow = new kakao.maps.InfoWindow({
	                content: '<div style="width:150px;text-align:center;padding:6px 0; top:-15px;"><%=s.getStoreName()%></div>'
	            });
	            infowindow.open(map, marker);
	
	            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	            map.setCenter(coords);
	        } 
	    });    
	     
	    
	    // 자리보여주기
	    $(".fullSeat").append($("<p>").html("사용중").css("color", "#ff7531"));
	    $("#countEmptySeat").html($(".emptySeat").length);
	    $("#countFullSeat").html($(".fullSeat").length);
	    //$(".fullSeat").append($("<p>").html("00:00"));
    
	    
	    
	    // 다음/예약하기 버튼 바꾸기
	 	let reservationCheck = false;
	 	let usePcMoney=0;
	 	<% if(loginMember!=null) { %>
	    $("#reOk").click(function() {
	    	if(typeof $('input:radio[name="time"]:checked').val() == 'undefined') {
	    		alert("이용시간을 선택해주세요.");
	    	} else if(checkOk == 0 && typeof $('input:radio[name="time"]:checked').val() != 'undefined') {
	    		$("#reOk").html("예약하기");
	    		$("#reCan").html("이전");
	    		
	    		$("#reStep1").toggle();
	    		$("#reStep2").toggle();
	    		
				console.log($('input:radio[name="time"]:checked').val());
				
				let usePcTime = $('input:radio[name="time"]:checked').val();
				usePcMoney = <%=s.getStorePrice()%> * usePcTime;
				console.log("usePcMoney : " + usePcMoney);
				let afterMoney = <%=loginMember.getCash()%> - usePcMoney;
				console.log("afterMoney : " + afterMoney);
				
				$("#willUseTime").html(usePcTime);
				$("#willPayMoney").html(usePcMoney);
				$("#afterMoney").html(afterMoney);
				
				if(afterMoney>=0) {
					reservationCheck = true;
				}
	    		
				$("#stepImg").attr("src","image/step2.png");
				
	    		$("#reservation").height("750px");
	    		
	    		// 예약하기에서 다시 실행
	    	    var mapContainer = document.getElementById('map2'), // 지도를 표시할 div 
	            mapOption = {
	                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	                level: 3 // 지도의 확대 레벨
	            };  

	    	    // 지도를 생성합니다    
	    	    var map = new kakao.maps.Map(mapContainer, mapOption); 
	    	
	    	    // 주소-좌표 변환 객체를 생성합니다
	    	    var geocoder = new kakao.maps.services.Geocoder();
	    	
	    	    // 주소로 좌표를 검색합니다
	    	    geocoder.addressSearch('<%=s.getStoreAddress()%>', function(result, status) {
	    	
	    	        // 정상적으로 검색이 완료됐으면 
	    	         if (status === kakao.maps.services.Status.OK) {
	    	
	    	            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	    	
	    	            // 결과값으로 받은 위치를 마커로 표시합니다
	    	            var marker = new kakao.maps.Marker({
	    	                map: map,
	    	                position: coords
	    	            });
	    	
	    	            // 인포윈도우로 장소에 대한 설명을 표시합니다
	    	            var infowindow = new kakao.maps.InfoWindow({
	    	                content: '<div style="width:150px;text-align:center;padding:6px 0; top:-15px;"><%=s.getStoreName()%></div>'
	    	            });
	    	            infowindow.open(map, marker);
	    	
	    	            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    	            map.setCenter(coords);
	    	        } 
	    	    });   
	    		
	    		checkOk++;
	    	} else {
	    		checkOk = 0;
	    		$("#reservation").toggle();
	    		console.log("토글이 닫혀서 되는거");
	    		if(reservationCheck) {
 	    			$.ajax({
	    				url:"<%=request.getContextPath()%>/reservation.do",
	    				type:"post",
	    				dataType:"json",
	    				data:{"userId":"<%=loginMember.getUserId()%>", "storeId":"<%=s.getStoreId()%>",
	    					"seat":selectedPcSeat, "pay":usePcMoney, "time":$('input:radio[name="time"]:checked').val(), "storeName":"<%=s.getStoreName()%>"},
	    				success:function(data) {
	    					console.log(data);
		    					alert("성공적으로 예약이 되었습니다.");
		    	    			$("#viewSeat").children().eq(1).children().children().children().eq(selectPcRow).children().eq(selectPcCol-1).
		    	    			addClass("fullSeat").html("사용중<br>" + data["endTime"]);
		    	    			$(".fullSeat").removeClass("emptySeat");	 
		    	    			$(".reSeat").removeClass("selectSeat");
		    	    			$(".reSeat").html("");
		    	    			$("#userCash").html(data["userCash"]);
		    	    			console.log(data["userCash"]);
		    	    			$("#reOk").html("다음");
		    					$("#reCan").html("취소");
		    	    			$("#reStep1").toggle();
		    		    		$("#reStep2").toggle();
		    					$("#reservation").height("310px");
		    				    $("#countEmptySeat").html($(".emptySeat").length);
		    				    $("#countFullSeat").html($(".fullSeat").length);
		    					console.log(<%=loginMember.getCash()%>);
	    				},error:(r,e,m)=>{
	    					console.log(r);
	    					console.log(e);
	    					console.log(m);
	    				}
	    			})
	    		} else {
	    			alert("예약실패(빈캐시부족) : 충전후 이용해주세요.")
	    			$("#reStep1").toggle();
		    		$("#reStep2").toggle();
		    		$("#reOk").html("다음");
					$("#reCan").html("취소");
					$("#reservation").height("310px");
					$(".reSeat").each(function() {
						if($(this).hasClass("selectSeat")) {
							$(this).html("");
							$(this).removeClass("selectSeat");
						}
					})
	    		}
	    		
	    	} 
	    })
	    <%}%>
		$("#reCan").click(function() {
			if(checkOk==0) {
				$(".reSeat").each(function() {
					if($(this).hasClass("selectSeat")) {
						$(this).html("");
						$(this).removeClass("selectSeat");
					}
				})
				checkOk=0;
	    		selectedPcSeat=0;
				$("#reservation").toggle();
			} else {
				$("#reStep1").toggle();
	    		$("#reStep2").toggle();
				$("#reOk").html("다음");
				$("#reCan").html("취소");
				$("#stepImg").attr("src","image/step1.png");
				$("#reservation").height("310px");
				
				checkOk = 0;
			}
		})
    })
	  
    $("#ectNum").change(function() {
    	$("#ectTime").val($(this).val());
    })
    
   	$.ajax({
   		url:"<%=request.getContextPath()%>/reservationCheck",
   		type:"post",
   		dataType:"json",
   		data:{"storeId":"<%=s.getStoreId()%>"},
   		success:function(data) {
   			var seatNums = 0;
   			$(".seat").each(function() {
   				if($(this).hasClass("fullSeat")) {
   					console.log(data);
   					$(this).html("사용중<br>" + data[seatNums]["seatEndTime"]);
   					seatNums++;
   				}
   			})
   		}
   	})
   	<%-- 
   	setInterval(function() {
   	   	$.ajax({
   	   		url:"<%=request.getContextPath()%>/reservationCheck",
   	   		type:"post",
   	   		dataType:"json",
   	   		data:{"storeId":"<%=s.getStoreId()%>"},
   	   		success:function(data) {
   	   			var seatFullNums = 0;
   	   			$(".seat").each(function() {
   	   				if($(this).hasClass("fullSeat")) {
   	   					console.log(data);
   	   					console.log(data[seatFullNums]);
   	   					console.log("seatFullNums : " + seatFullNums);
   	   					$(this).html("사용중<br>" + data[seatFullNums]["seatEndTime"]);
   	   					seatFullNums++;
   	   				} else {
   	   					$(this).addClass("emptySeat");
   	   					$(this).removeClass("fullSeat");
   	   					$(this).html("");
   	   					$(this).append($("<p>").html("사용가능"));
   	   					$(this).append($("<button>").html("예약하기").addClass("reservationBtn"));
   	   				}
   	   			})
   	   			let colCount = 1;
   	   			$("#viewSeat table td").each(function() {
   	   				if($("#viewSeat table td button").hasClass("reservationBtn")) {
   	   					$(this).children().eq(1).val(colCount);
   	   					console.log($(this).children().eq(1));
   	   				}
   	   				colCount++;
   	   				console.log(colCount);
   	   			})
   	   		}
   	   	})
   	}, 60000) --%>
	  
   	
   		var intervalCount = 0;
<%if (loginMember != null) {%>
	    
		var checkOk=0;
 		var selectedPcSeat=0;
	    
	 	// 예약하기 띄우기
	    $(document).on("click",".reservationBtn",function() {
	    	checkOk=0;
	 	    selectedPcSeat=0;
	 		selectPcCol=0;
	 	  	selectPcRow=0;
	    	$("#selectedSeatText").html($(this).val());
	    	selectedPcSeat = $(this).val();
	    	console.log("$(this).val() : " + $(this).val())
	    	selectPcRow = Math.floor($(this).val() / <%=ss.getCol()%>);
	    	selectPcCol = $(this).val() % <%=ss.getCol()%>;
	    	
	    	console.log($(this).val());

/* 			if(intervalCount != 0) {
				selectPcCol += $(".fullSeat").length;
			} */
	    	
	    	if(selectPcCol == 0) {
	    		selectPcRow -= 1;
	    	}
	    	
	    	
	    	
	    	console.log("로우 : " + selectPcRow + ", 컬 : " + selectPcCol);
	    	
	    	$("#viewSeat2").children().eq(1).children().children().eq(selectPcRow).children().eq(selectPcCol-1).addClass("selectSeat");
	    	$(".selectSeat").html("선택");
	    	$("#stepImg").attr("src","image/step1.png");
	    	$("#reservation").toggle();
	    })
	    
	    <%} else {%>
	    $(".reservationBtn").click(function() {
	    	alert('로그인이 필요합니다!');
	    })
	    <%}%>
	    
    </script>


</div>

<script src="js/store.js?ver=2"></script>
<script type="text/javascript" src="js/totalSearch.js?ver=2"></script>
</body>
</html>