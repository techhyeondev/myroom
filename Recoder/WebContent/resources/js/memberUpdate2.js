$(document).ready(function(){
    $(".container").fadeIn(1000);
    $(".s2class").css({"color":"#white"});
    $(".s1class").css({"color":"#white"}); 
    $("#left").removeClass("left_hover");
    $("#right").addClass("right_hover");
    $("#attachFile").css({"display":"none"});
    $(".terms").css({"display":"none"});
    $("#left").removeClass("left_hover");
    $(".s2class").css({"color":"#white;"});
    $(".s1class").css({"color":"#748194"});
    $("#right").addClass("right_hover");
 });


//멤버-----------------------------------------------------------------------

//회원가입 유효성 검사 객체
var validateCheck = {
	"password" : true,
	"confirmpassword" : true,
	"email" : true,
	"nickname" : true,
	"usertel" : true
}


//일반 이메일 유효성 검사
$("#email").on("input",function(){
    var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/; // 4글자 아무단어 @ 아무단어 . * 3
    
    var value = $("#email").val();
    if(!regExp.test(value)){
        $("#checkEmail").text("이메일 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.email = false;
    }else{
        $("#checkEmail").text("유효한 이메일 형식입니다.").css("color","green");
        validateCheck.email = true;
    }
});

//공인 이메일 유효성 검사
$("#email2").on("input",function(){
    var regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/; // 4글자 아무단어 @ 아무단어 . * 3
    
    var value = $("#email2").val();
    if(!regExp.test(value)){
        $("#checkEmail2").text("이메일 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.email = false;
    }else{
        $("#checkEmail2").text("유효한 이메일 형식입니다.").css("color","green");
        validateCheck.email = true;
    }
});

//비밀번호 검사
$("#password, #confirmpassword").on("input",function(){
	var regExp =/^[a-zA-Z\d]{6,20}$/;
    var value1= $("#password").val(); 
	var value2 = $("#confirmpassword").val(); 
	
	if(!regExp.test(value1)){
        $("#checkPw").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.password = false;
    }else{
		 $("#checkPw").text("유효한 비밀번호 형식입니다.").css("color","green");
        validateCheck.password = true;
	}
	//비밀번호가 유효하지 않은 상태에서 비밀번호 확인 작성 시
    if(!validateCheck.password && value2.length > 0){
        swal("유효한 비밀번호를 먼저 작성해주세요");
        $("#confirmpassword").val(""); 
        $("#password").focus(); 
    }else{
        // + 비밀번호, 비밀번호 확인의 일치여부 
        if(value1.length == 0 || value2.length == 0){
            $("#checkPw2").html("&nbsp;");
        }else if(value1 == value2){
            $("#checkPw2").text("비밀번호 일치").css("color","green");
            validateCheck.confirmpassword = true;
        }else{
            $("#checkPw2").text("비밀번호 불일치").css("color","red");
            validateCheck.confirmpassword = false;
        }
    }
});

//공인 비밀번호 검사
$("#password2, #confirmpassword2").on("input",function(){
	var regExp =/^[a-zA-Z\d]{6,20}$/;
    var value1= $("#password2").val(); 
	var value2 = $("#confirmpassword2").val(); 
	
	if(!regExp.test(value1)){
        $("#checkPwB").text("비밀번호 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.password = false;
    }else{
		 $("#checkPwB").text("유효한 비밀번호 형식입니다.").css("color","green");
        validateCheck.password = true;
	}
	//비밀번호가 유효하지 않은 상태에서 비밀번호 확인 작성 시
    if(!validateCheck.password && value2.length > 0){
        swal("유효한 비밀번호를 먼저 작성해주세요");
        $("#confirmpassword2").val(""); 
        $("#password2").focus(); 
    }else{
        // + 비밀번호, 비밀번호 확인의 일치여부 
        if(value1.length == 0 || value2.length == 0){
            $("#2checkPw2").html("&nbsp;");
        }else if(value1 == value2){
            $("#2checkPw2").text("비밀번호 일치").css("color","green");
            validateCheck.confirmpassword = true;
        }else{
            $("#2checkPw2").text("비밀번호 불일치").css("color","red");
            validateCheck.confirmpassword = false;
        }
    }
});

//닉네임 유효성 검사
$("#nickname").on("input",function(){
	 var regExp = /^[가-힣a-zA-Z\d]{3,10}$/; //
    
    var value = $("#nickname").val();
    if(!regExp.test(value)){
        $("#checkNick").text("닉네임 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.nickname = false;
    }else{
       $.ajax({
			url:"nickDupCheck.do",
			data: {"nickname": value},
			type: "post",
			success: function(result){
				if(result == 0){ // 중복되지 않은 경우
                    $("#checkNick").text("사용 가능한 닉네임 입니다.").css("color","green");
                    validateCheck.nickname = true;
               }else{
                    $("#checkNick").text("이미 사용중인 닉네임 입니다.").css("color","red");
                    validateCheck.nickname = false;
               }
			},
			error: function(){
				console.log("닉네임 중복 검사 실패");
			}
		});
    }
});

//공인중개사 닉네임 유효성 검사
$("#nickname2").on("input",function(){
	 var regExp = /^[가-힣a-zA-Z\d]{3,10}$/; //
    
    var value = $("#nickname2").val();
    if(!regExp.test(value)){
        $("#checkNick2").text("닉네임 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.nickname = false;
    }else{
       $.ajax({
			url:"nickDupCheck.do",
			data: {"nickname": value},
			type: "post",
			success: function(result){
				if(result == 0){ // 중복되지 않은 경우
                    $("#checkNick2").text("사용 가능한 닉네임 입니다.").css("color","green");
                    validateCheck.nickname = true;
               }else{
                    $("#checkNick2").text("이미 사용중인 닉네임 입니다.").css("color","red");
                    validateCheck.nickname = false;
               }
			},
			error: function(){
				console.log("닉네임 중복 검사 실패");
			}
		});
    }
});

//전화번호 유효성 검사
// 전화번호 유효성 검사
$("#usertel").on("input", function(){
    // input 태그에 number 타입은 maxvalue 설정이 안되기때문에
    // 전화번호 input 태그에 4글자 초과 입력하지 못하게 하는 이벤트
   if ($(this).val().length > 13) {
      $(this).val( $(this).val().slice(0, 13));
    }
    
    var regExp = /^[\d]{3}-[\d]{4}-[\d]{4}$/;
// /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
    var value = $("#usertel").val();

    if(!regExp.test(value)){
        $("#checkPhone").text("전화번호 형식이 유효하지 않습니다.").css("color", "red");
        validateCheck.usertel = false;
    }else{
        $("#checkPhone").text("유효한 형식의 전화번호 입니다.").css("color", "green");
        validateCheck.usertel = true;
    }

});


//전화번호 유효성 검사
//공인 전화번호 유효성 검사
$("#usertel2").on("input", function(){
    // input 태그에 number 타입은 maxvalue 설정이 안되기때문에
    // 전화번호 input 태그에 4글자 초과 입력하지 못하게 하는 이벤트
   if ($(this).val().length > 13) {
      $(this).val( $(this).val().slice(0, 13));
    }
    
    var regExp = /^[\d]{3}-[\d]{4}-[\d]{4}$/;
// /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
    var value = $("#usertel2").val();

    if(!regExp.test(value)){
        $("#checkPhone2").text("전화번호 형식이 유효하지 않습니다.").css("color", "red");
        validateCheck.usertel = false;
    }else{
        $("#checkPhone2").text("유효한 형식의 전화번호 입니다.").css("color", "green");
        validateCheck.usertel = true;
    }

});


	
//------------------------------------------------------------------
function validateMember(){
	
	
	
	// 유효성 검사 여부 확인
    for(var key in validateCheck){
        if(!validateCheck[key]){
            var msg;
            switch(key){
                case "password":  
                case "confirmpassword": msg = "비밀번호가"; break;
                case "nickname": msg = "닉네임이 "; break;
                case "usertel": msg = "전화번호가"; break;
                case "email": msg = "이메일이"; break;
            }

            swal(msg + " 유효하지 않습니다.");

            $("#"+key).focus();

            return false; 
        }
    }

	


//공인중개사 유효성 검사------------------------------
function validateBroker(){
	
	
	// 유효성 검사 여부 확인
    for(var key in validateCheck){
        if(!validateCheck[key]){
            var msg;
            switch(key){
                case "userid"  : msg = "아이디가"; break;
                case "password":  
                case "confirmpassword": msg = "비밀번호가"; break;
                case "nickname": msg = "닉네임이 "; break;
                case "usertel": msg = "전화번호가"; break;
                case "email": msg = "이메일이"; break;
            }

            swal(msg + " 유효하지 않습니다.");

            $("#"+key).focus();

            return false; 
        }
    }

}


}
