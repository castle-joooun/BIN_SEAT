
$(function(){
    $("#allCheck").click(function(){
        allCheckFunc($(this));
        var one = $(".chkone")
         
        console.log(one);
        
    })
    
})
function oneCheck(){
   
    oneCheckFunc(event.target);
    
}
function allCheckFunc(obj){
    $(".chkone").prop("checked",$(obj).prop("checked"));
}
function oneCheckFunc(obj){
    
    var allObj = $("#allCheck");
    var objName = $(obj).attr("name");
   if($(obj).prop("checked")){
       checkBoxLength = $("[name="+objName+"]").length;
       checkedLength = $("[name="+objName+"]:checked").length;
//       console.log(checkBoxLength);
//       console.log(checkedLength);
       if(checkBoxLength ==checkedLength){
           allObj.prop("checked",true);
       }else{
           allObj.prop("checked",false);
       }
   }else{
       allObj.prop("checked",false);
   }

}   
//전체선택후 승인시 보내는 로직
function idPlus(){
	var str = "";
	$(".chkone").each(function(){
		if($(this).is(":checked")){
			
			str += $(this).val()+", ";
		}
	})
	 console.log(str);
	$("#submitIds").val(str);
	$("#apprSubmit").submit();
}
