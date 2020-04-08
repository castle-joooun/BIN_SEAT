
// 슬라이드 기능
$("#next").click(function () {
    var first = $(".interior").first();
    $("#imgSlide").append(first);
})
$("#back").click(function () {
    var last = $(".interior").last();
    $("#imgSlide").prepend(last);
})

// $("#commentBtn").click(function () {
//     let div = $("<div>").attr("class", "commentDiv");
//     let userTable = $("<table>").attr("class", "userTable");
//
//     // user 첫번째 tr
//     let userTr1 = $("<tr>");
//     let userInName = $("<th>").html("userID");
//
//     userInName.attr("colspan", "2");
//     userTr1.append(userInName);
//     let userDate = new Date();
//     userTr1.append($("<td>").attr("class", "userDate").html(
//         userDate.getFullYear() + "년 " +
//         (userDate.getMonth() + 1) + "월 " +
//         userDate.getDate() + "일 " +
//         userDate.getHours() + ":" + userDate.getMinutes()
//     ));
//
//     // user 두번째 tr
//     let comment = $("#commentInput").val();
//     let userTr2 = $("<tr>");
//     let storeRe = $("<button>").attr({
//         "class": "reB"
//     });
//
//     // user click function
//     $(storeRe).click(function () {
//
//         console.log("대댓글!");
//         
//         // re input
//         let textarea = $("<textarea>").attr("class", "storeTextarea");
//         let storeBtn = $("<button>").attr("class", "storeBtn").html("등록");
//         let reTable = $("<table>").attr("class", "reTable");
//         let reTr = $("<tr>");
//         reTr.append($("<td>").append(textarea));
//
//
//         // rereply function
//         $('#reB').click(function () {
//             let storeTable = $("<table>").attr("class", "storeTable");
//
//             // store 1st tr
//             let storeTr1 = $("<tr>");
//             let storeArrow = $("<img>").attr({
//                 //            	"src": "image/arrow.png",
//                 "class": "storeArrow"
//             });
//             storeTr1.append($("<td>").attr({
//                 "rowspan": "2",
//                 "class": "tdTwo"
//             }).append(storeArrow));
//             storeTr1.append($("<th>").html("사장님"));
//
//             let storeDate = new Date();
//             storeTr1.append($("<td>").attr("class", "storeDate").html(
//                 storeDate.getFullYear() + "년 " +
//                 (storeDate.getMonth() + 1) + "월 " + 7411+ 
//                 storeDate.getDate() + "일 " +
//                 storeDate.getHours() + ":" + storeDate.getMinutes()
//             ))
//
//             // store 2rd tr
//             let storeTr2 = $("<tr>");
//             let storeComment = $(this).parent().prev().children().val();
//             storeTr2.append($("<td>").attr("colspan", "2").html(storeComment));
//
//             console.log(storeComment);
//
//             // 합치기
//             storeTable.append(storeTr1);
//             storeTable.append(storeTr2);
//             $(this).parents(".commentDiv").append(storeTable);
//
//             //삭제
//             $(this).parents(".commentDiv").children(".reTable").html("");
//         })
//
//         reTr.append($("<td>").append(storeBtn));
//         reTable.append(reTr);
//
//
//
//         $(this).parent().parent().parent().parent().append(reTable);
//
//     })
//
//     userTr2.append($("<td>").attr("class", "tdTwo").append(storeRe));
//     userTr2.append($("<td>").attr("colspan", "2").append(comment));
//
//
//     // id=comment에 붙이기
//     userTable.append(userTr1);
//     userTable.append(userTr2);
//     div.append(userTable);
//
//     $("#comment").append(div);
// })
//

//자리보기

// 버튼 누르면 닫힘
$("#viewSeatBtn").click(function () {

    if($(this).css("display") != "none") {
        
        $("#clickNone").slideUp("slow");
        $("#storeTitleBox").css("borderBottom", "0");
        $(this).css("display", "none");
        
        // 닫혔는지 알아보는 기능
        $("#onOff").attr("value","0");
        
        setTimeout(function() {
            $("#viewSeat").slideDown("slow");
        }, 500);
    }
})

// 버튼 누르면 열림
$("#storeTitle").click(function() {

	// 닫혔는지 알아보는 기능
    $("#onOff").attr("value","1");
	
    console.log(1111111);
    $("#viewSeat").slideUp("slow");

    $("#clickNone").slideDown("slow");
    $("#storeTitleBox").css({"border-bottom":"solid 1px #dbdbdb", "cursor":"pointer"});
    setTimeout(function() {
    	$("#viewSeatBtn").show();
    }, 500);
    
    
})


// 현재시간 표시
var clockTarget = document.getElementById("clock");

function clock() {
    var date = new Date();

    var hours = date.getHours();
    var minutes = date.getMinutes();

    clockTarget.innerText = `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes }`  : minutes }`;
}
setInterval(clock, 1000);
