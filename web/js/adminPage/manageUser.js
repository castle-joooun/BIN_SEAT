//유저 페이지 접속시 메뉴 바 색상 변경
//$(function(){
//	$("#main>a").css("color","black");
//	$("#main>a:hover").css("color","#ff7531");
//	$("#userPage>a").css("color","#ff7531");
//})

//검색타입 설정시 변하는 효과
function choiceType(){
	let type  = $("#searchType").val();
	let username = $("#search-username");
	let gender = $("#search-gender");
	let phone = $("#search-phone");

	username.hide();
	gender.hide();
	phone.hide();
	$("#search-"+type).css("display","inline-block");
	$("input[name='searchKeyword']").val("");
	 
}
//전체회원 조회 화면에서 검색버튼 눌렀을 때 form전송 
function doSubmit(data){
	
	$(event.target).parent().submit();
	 
}

//$(function(){
//	$("#searchType").change(()=>{
//		
//		/* var cho = $(this).children("option:selected").text(); */
//		let type=$("#searchType").val();
//		let userId=$("#search-userId");
//		let username=$("#search-username");
//		let gender=$("#search-gender");
//		userId.hide();
//		username.hide();
//		gender.hide();
//		$("#search-"+type).css("display","inline-block");
//		
//	
//	})
//	$("#searchType").trigger("change");
//})
//전체선택, 전체해제에 관한 설정
$(function(){
    $("#allCheck").click(function(){
        allCheckFunc($(this));
        
    })
    $(".chkone").each(function(){
        $(this).click(function(){
        	
            oneCheckFunc($(this));
        })
    })
})
function allCheckFunc(obj){
    $(".chkone").prop("checked",$(obj).prop("checked"));
}
function oneCheckFunc(obj){
    console.log("test");
    var allObj = $("#allCheck");
    var objName = $(obj).attr("name");
   if($(obj).prop("checked")){
       checkBoxLength = $("[name="+objName+"]").length;
       checkedLength = $("[name="+objName+"]:checked").length;
       console.log(checkBoxLength);
       console.log(checkedLength);
       if(checkBoxLength ==checkedLength){
           allObj.prop("checked",true);
       }else{
           allObj.prop("checked",false);
       }
   }else{
       allObj.prop("checked",false);
   }

}   

//목록수 변경시
$(function(){
	$("#numPerPage").change(function(){
		var cPage = $("#cPage").val();
		var numPerPage = $(this).val();
		requestData(cPage,numPerPage);
	})
})
//검색 후 정렬하기 
//검색 후 목록수 변경시 
$(function(){
	$("#numPerPageSearch").change(function(){
		var cPage = $("#cPage").val();
		var numPerPage = $(this).val();
		var type = $("#searchType").val();
		var key = $("#search-"+type).children("[name='searchKeyword']").val();
		console.log(type);
		console.log(key);
		searchKeyType(type,key,cPage,numPerPage);
		
	})
})


//검색타입 설정후 검색했을 때
function searchAjax(){
	var key = $(event.target).siblings("[name='searchKeyword']").val();
	var type = $(event.target).siblings("[name='searchType']").val();
	var numPerPage = $("#numPerPageSearch").val();
	searchKeyType(type,key,1,numPerPage);
	
}
