// 모달창 띄우기
var modal = document.getElementById('id01');

// 모달창 바깥 아무데나 누르면 꺼짐
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

// 메인메뉴 클릭 -> 서브메뉴 나타나게
// main
var menubarMain = document.getElementById("main");
var mainSub = document.getElementById("mainSub");
menubarMain.onclick = function() {
    mainSub.toggle
}




// -------------------------------------------------------------------------------------------------



// 검색창
function change() {
    let searchValue = document.getElementById("searchBox").value;
    if (searchValue !== "") {
        console.log(searchValue);
        $("searchBox").val() = "";
        return searchValue;
    }
}
$("#searchBoxGra input[type='submit']").click(function() {
    if(!($("#searchBoxGra input[type='text']").val() === null || $("#searchBoxGra input[type='text']").val() === "")) {
        $("#searchForm").attr("onSubmit", true);
    }
})

// 시계
var clockTarget = document.getElementById("clock");

function clock() {
    var date = new Date();

    var hours = date.getHours();
    var minutes = date.getMinutes();

    clockTarget.innerText = `${hours < 10 ? `0${hours}` : hours}:${minutes < 10 ? `0${minutes }`  : minutes }`;
}

setInterval(clock, 1000);

// 슬라이드 기능
var slidePosition = 2;
var slideWidth = 100;
$("#next").click(function() {

    if(slidePosition < 2) {
        slidePosition++;
        slideWidth+=100;
        $("#slideMove").css("marginLeft",("-" + slideWidth+"%"));
        $("#pos"+slidePosition).prop('checked', true);
    }
})
$("#back").click(function() {
    if(slidePosition > 1) {
        slidePosition--;
        slideWidth-=100;
        $("#slideMove").css("marginLeft",("-" + slideWidth+"%"));
        $("#pos"+slidePosition).prop('checked', true);
    }
})	

