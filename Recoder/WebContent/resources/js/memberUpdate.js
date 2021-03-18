var validateCheck = {
    "id" : false,
    "pwd1" : true,
    "pwd2" : true,
    "nickname" : false,
    "phone2" : false,
    "email" : false
}


// 이메일 유효성 검사
$("#userEmail").on("input",function(){
    var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/; // 4글자 아무단어 @ 아무단어 . * 3

    var value = $("#userEmail").val();
    if(!regExp.test(value) ){
        $("#checkEmail").text("이메일 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.email = false;
    }else{
        $("#checkEmail").text("유효한 이메일 형식입니다.").css("color","green");
        validateCheck.email = true;
    }
});

//비밀번호 유효성 검사
//영어 대소문자 + 숫자, 총 6~12글자
//+ 비밀번호, 비밀번호 확인의 일치 여부
//+ 비밀번호를 입력하지 않거나 유효하지 않은 상태로
// 비밀번호 확인을 작성하는 경우

$("#pw1, #pw2").on("input", function(){
    // 비밀번호 유효성 검사
    var regExp = /^[a-zA-Z\d]{6,12}$/;

    var v1 = $("#pw1").val();
    var v2 = $("#pw2").val();

    console.log("v1:" + v1);


        if(!regExp.test(v1)){
            $("#checkPwd1").text("비밀번호 형식이 유효하지 않습니다.")
            .css("color", "red");
            validateCheck.pwd1 = false;
        } else{
            $("#checkPwd1").text("유효한 비밀번호 형식입니다.")
            .css("color", "green");
            validateCheck.pwd1 = true;
        }
    


    // 비밀번호가 유효하지 않은 상태에서 비밀번호 확인 작성 시

    if(!validateCheck.pwd1 && v2.length > 0){
        swal("유효한 비밀번호를 먼저 작성해주세요.");
        $("#pw2").val(""); // 비밀번호 확인에 입력한 값 삭제
        $("#pw2").focus();
    }else {
        // 비밀번호, 비밀번호 확인의 일치 여부
       if(v1.length == 0 || v2.length == 0){
         $("#checkPwd2").html("&nbsp;");
      }else if(v1 == v2){
            $("#checkPwd2").text("비밀번호 일치")
            .css("color", "green");
            validateCheck.pwd2 = true;
        }else{
            $("#checkPwd2").text("비밀번호 불일치")
            .css("color", "red");
            validateCheck.pwd2 = false;
        }
    }

});

//전화번호 유효성 검사
$(".phone").on("input", function(){
    // 전화번호 input 태그에 4글자 초과 입력하지 못하게 하는 이벤트
    if ($(this).val().length > 4) {
        $(this).val( $(this).val().slice(0, 4));
    }

    var regExp1 = /^\d{3,4}$/;
    var regExp2 = /^\d{4}$/;

    var v1 = $("#phone2").val();
    var v2 = $("#phone3").val();

    if(!regExp1.test(v1) || !regExp2.test(v2)){
        $("#checkPhone").text("전화번호가 유효하지 않습니다.")
        .css("color", "red");
        validateCheck.phone2 = false;
    }else{
        $("#checkPhone").text("유효한 형식의 전화번호 입니다.")
        .css("color", "green");
        validateCheck.phone2 = true;
    }

});

// 이메일 유효성 검사
$("#userNickname").on("input",function(){
    var regExp = /^[가-힣a-zA-Z\d]{3,6}$/; // 4글자 아무단어 @ 아무단어 . * 3

    var value = $("#userNickname").val();
    if(!regExp.test(value) ){
        $("#checkNick").text("닉네임 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.email = false;
    }else{
        // $("#checkNick").text("유효한 닉네임 형식입니다.").css("color","green");
        // validateCheck.email = true;
        // ajax를 이용한 실시간 아이디 중복 검사
        $.ajax({
            url : "nickDupCheck.do",
            data : {"nickname" : value},
            type : "post",
            success : function(result){
                // $("#checkId").text("유효한 아이디 형식입니다.").css("color", "green");

                if(result == 0){ // 중복되지 않은 경우
                    $("#checkNick").text("사용 가능한 닉네임 형식입니다.").css("color", "green");
                    validateCheck.id = true;
                }else{
                    $("#checkNick").text("이미 사용중인 닉네임 입니다.").css("color", "red");
                    validateCheck.id = false;
                }

            },
            error : function(){
                console.log("아이디 중복 검사 실패");
            }

        });
    }
});



// //닉네임 유효성 검사
// $("#userNickname").on("input",function(){
//     var regExp = /^[가-힣a-zA-Z\d]{3,6}$/; //
//     var value = $("#userNickname").val();
//    if(!regExp.test(value)){
//        $(".checkNick").text("닉네임 형식이 유효하지 않습니다.").css("color","red");
//        validateCheck.nickname = false;
//    }else{
//       $.ajax({
//            url:"nickDupCheck.do",
//            data: {"nickname": value},
//            type: "post",
//            success: function(result){
//                if(result == 0){ // 중복되지 않은 경우
//                    $(".checkNick").text("사용 가능한 닉네임 입니다.").css("color","green");
//                    validateCheck.nickname = true;
//               }else{
//                    $(".checkNick").text("이미 사용중인 닉네임 입니다.").css("color","red");
//                    validateCheck.nickname = false;
//               }
//            },
//            error: function(){
//                console.log("닉네임 중복 검사 실패");
//            }
//        });
//    }
// });



function validate(){
        // 유효성 검사 여부 확인
        console.log("체크");
        for(var key in validateCheck){
            if(!validateCheck[key]){
                var msg;
                switch(key){
                    case "pwd1":  
                    case "pwd2": msg = "비밀번호가"; break;
                    case "nickname": msg = "닉네임이 "; break;
                    case "phone2": msg = "전화번호가"; break;
                    case "userEmail": msg = "이메일이"; break;
                }
    
                swal(msg + " 유효하지 않습니다.");
    
                $("#"+key).focus();
    
                return false; 
            }
        }
}