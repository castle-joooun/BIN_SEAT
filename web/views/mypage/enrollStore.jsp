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
						매장등록
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
						<td>
							<input type="text" id="storeName" placeholder='PC방이름을 입력해주세요.'>
						</td>
					</tr>
					<tr>
						<td>
							매장 전화번호
						</td>
						<td>
							<input type="text" id="storePhone" placeholder="000-0000-0000">
						</td>
					</tr>
					<tr>
						<td>
							영업시간
						</td>
						<td>
							<input type="time" id="storeTimestart">
							~
							<input type="time" id="storeTimeclose">
						</td>
					</tr>
					<tr>
						<td>
							매장정보
						</td>
						<td>
						 	<input type="text" id="storeInfo" placeholder=' , 로 구분하여 작성해주세요.'>
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
							부대시설
						</td>
						<td>
							<label>
								매점<input type="checkbox" name="storeFacirity" class="storeFacirity" value="매점">
							</label>
							<label>
								카페<input type="checkbox" name="storeFacirity" class="storeFacirity" value="카페">
							</label>
							<label>
								난방시설<input type="checkbox" name="storeFacirity " class="storeFacirity" value="난방시설">
							</label>
							<label>
								공기청정기<input type="checkbox" name="storeFacirity"  class="storeFacirity" value="공기청정기">
							</label>
							<label>
								흡연시설<input type="checkbox" name="storeFacirity" class="storeFacirity" value="흡연시설">
							</label>
							<label>
								제빙기<input type="checkbox" name="storeFacirity" class="storeFacirity" value="제빙기">
							</label>
							<label>
								주차시설<input type="checkbox" name="storeFacirity" class="storeFacirity" value="주차시설">
							</label>
							
						</td>
					</tr>
					<tr>
						<td>
							위치
						</td>
						<td>
				            <input type="text" class="postNum" id="sample4_postcode" placeholder="우편번호" readonly="readonly">
				            <input type="button" onclick="findAddress()" value="우편번호 찾기"><br>
				            <input type="text" class="enrollInput" id="sample4_roadAddress" name="address1" placeholder="도로명주소" readonly="readonly">
				            <input type="hidden" id="sample4_jibunAddress" placeholder="지번주소" readonly="readonly">
				            <span id="guide" style="color:#999;display:none"></span>
				            <input type="text" class="enrollInput" id="sample4_detailAddress" name="address2" placeholder="상세주소">
				             <input type="hidden" id="sample4_extraAddress" placeholder="참고항목">
						</td>
					</tr>
						<tr>
							<td></td>
							<td id="map" style="height:300px;"></td>
						</tr>
					<tr>
						<td>
							시간당 금액
						</td>
						<td>
							<input type="text" id="storePrice">
						</td>
					</tr>
					<tr>
						<td>
							매장로고
						</td>
						<td>
							<input type="file" id="fileupload">
							<div id="filemiribogi">
								
							</div>
						</td>
					</tr>
					<tr>
						<td>
							매장인테리어사진
						</td>
						<td>
							<input type="file" id="itfileupload" multiple>
							<div id='filemiribogilist'>
							
							</div>
						</td>
					</tr>
				</table>
				<button id="storeEnrollbtn">등록</button>
			</div>
			


<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

   // 지도를 생성합니다    
   var map = new kakao.maps.Map(mapContainer, mapOption); 

   // 주소-좌표 변환 객체를 생성합니다
   var geocoder = new kakao.maps.services.Geocoder();

		
		
	   function findAddress() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수

	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample4_postcode').value = data.zonecode;
	                document.getElementById("sample4_roadAddress").value = roadAddr;
	                //document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("sample4_extraAddress").value = '';
	                }
	                
	                var address = $("#sample4_roadAddress").val(); //도로명주소 변수화
	             // 주소로 좌표를 검색합니다
	         	   geocoder.addressSearch(address, function(result, status) {

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
	         	               content: '<div style="width:150px;text-align:center;padding:6px 0; top:-15px;"></div>'
	         	           });
	         	           infowindow.open(map, marker);

	         	           // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	         	           map.setCenter(coords);
	         	       }  
	         	   });   
	                
	            }
	        }).open();
	    }
	
	   
	
			$(function(){
			
				
				$("#storeEnrollbtn").click(function(){
					var storeName=$("#storeName").val();
					var storePhone=$("#storePhone").val();
					var storeTimestart=$("#storeTimestart").val();
					var storeTimeclose=$("#storeTimeclose").val();
					var storeInfo=$("#storeInfo").val();
					var storeAddress=$("#sample4_roadAddress").val();
					var storePrice=$("#storePrice").val();
					var storeFacirity=[];
					var userId="<%=loginMember.getUserId()%>";
					var form=$("#frm").serialize();
					var fileupload=$("#fileupload").val();
					var itfileupload=[]
					
					console.log(fileupload);
					console.log(itfileupload);
					
					const fd=new FormData();
					$.each($("#itfileupload")[0].files,function(i,item){
						fd.append("empty"+i,item);
					});
					
					$('input[name="storeFacirity"]:checked').each(function(i){//체크된 리스트 저장
						storeFacirity.push($(this).val());
	                });
			
					$.ajax({
						url:"<%=request.getContextPath()%>/enroll/fileUp",
						data:fd,
						type:"post",
						processData:false,
						contentType:false,
						success:function(data){
							alert("등록 성공");
						 	$("#fileupload").val("");
						},
						error:function(r,e,m){
							alert("등록 실패");
						}
					})
					
					$.ajax({
						url:"<%=request.getContextPath()%>/enroll/store",
						data:{
							  "userId":userId,
							  "storeName":storeName,
							  "storePhone":storePhone,
							  "storeTimestart":storeTimestart,
							  "storeTimeclose":storeTimeclose,
							  "storeInfo":storeInfo,
							  "storeFacirity":storeFacirity,
							  "storeAddress":storeAddress,
							  "storePrice":storePrice,
							  "fileupload":fileupload
						},
						traditional : true,
						dataType:"json",
						type:"post",
						success:function(data){
						},
					})
				});
				
				$("#fileupload").change(function(){   //미리버기
					const reader=new FileReader();
					$.each($(this)[0].files,function(i,item){
						const reader=new FileReader();
						reader.onload=function(e){
							var img=$("<img>").attr("src",e.target.result)
							.css({width:"100px",height:"100px"});
							$("#filemiribogi").html(img);
						}
						reader.readAsDataURL(item);
					});
					
				})
				
				$("#itfileupload").change(function(){   //미리버기
					const reader=new FileReader();
					$.each($(this)[0].files,function(i,item){
						const reader=new FileReader();
						reader.onload=function(e){
							var img=$("<img>").attr("src",e.target.result)
							.css({width:"100px",height:"100px"});
							$("#filemiribogilist").append(img);
						}
						reader.readAsDataURL(item);
					});
					
				})
			})
			
			
			
			</script>
			
