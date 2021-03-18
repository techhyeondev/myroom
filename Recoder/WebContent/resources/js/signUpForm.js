$(document).ready(function(){
    $(".container").fadeIn(1000);
    $(".s2class").css({"color":"#white"});
    $(".s1class").css({"color":"#white"}); 
    $("#left").removeClass("left_hover");
    $("#right").addClass("right_hover");
    $(".Brokersignup").css({"display":"none"});
    $(".membersignup").css({"display":""});
    $("#attachFile").css({"display":"none"});
    $(".terms").css({"display":"none"});
 });
 $("#right").click(function(){
    $("#left").removeClass("left_hover");
    $(".s2class").css({"color":"#white;"});
    $(".s1class").css({"color":"#748194"});
    $("#right").addClass("right_hover");
    $(".Brokersignup").css({"display":"none"});
    $(".membersignup").css({"display":""});
 });

//공인중개사클릭
 $("#left").click(function(){
    $(".s1class").css({"color":"#white"});
    $(".s2class").css({"color":"#748194"}); 
    $("#right").removeClass("right_hover");
    $("#left").addClass("left_hover");
    $(".membersignup").css({"display":"none"});
    $(".Brokersignup").css({"display":""});
 });

 /* 약관 열기 */
   $(".open").on("click", function(){
      if($(this).next().css('display')!='none'){	
         $(this).next().hide();
         $(".c1").css("height","1450px");
         $(".c2").css("height","1320px");
			if(!$("#brokerImg").hasClass("added")){
				 $(".c1").css("height","1270px");
        		 $(".c2").css("height","1170px");
			}
      }else{
         $(".open").next().hide();
         $(this).next().show();
         $(".c1").css("height","1450px");
         $(".c2").css("height","1320px");
			if($("#brokerImg").hasClass("added")){
				 $(".c1").css("height","1750px");
        		 $(".c2").css("height","1620px");
			}
      }

   });

/* 약관 전체동의 */
$("#checkAll").on("click", function(){
   if($("#checkAll").is(":checked")){
      $(".n-check").prop("checked", true);
   }else{
      $(".n-check").prop("checked", false);
   }
});

$("#checkAll1").on("click", function(){
   if($("#checkAll1").is(":checked")){
      $(".n-check1").prop("checked", true);
   }else{
      $(".n-check1").prop("checked", false);
   }
});

/* 한개 체크박스 선택해제시 전체선택 체크박스해제 */
$(".agree-item").on("click", function(){
   $("#checkAll").prop("checked", false);
$("#checkAll1").prop("checked", false);
}); 

/* 파일선택 버튼 클릭시 파일선택 열리게 */
(function(){
   $("#img0").hide();

   $("#fileselect").on("click", function(){
      $("#img0").click();
   })
})();

//파일 미리보기
// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
  function LoadImg(value, num) {
	  // value.files : 파일이 업로드되어 있으면 true
	  // value.files[0] : 여러 파일 중 첫번째 파일이 업로드 되어있으면 true
		if(value.files && value.files[0]){ // 해당 요소에 업로드된 파일이 있을 경우
			
			var reader = new FileReader();
			// 자바스크립트 FileReader
      // 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는
      // File 혹은 Blob객체를 이용해 파일의 내용을 읽고
      // 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체
	
      reader.readAsDataURL(value.files[0]);
   		// FileReader.readAsDataURL()
      // 지정된의 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.

			reader.onload = function(e){
			// FileReader.onload
			// load 이벤트의 핸들러. 
			// 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.
			
			// 읽어들인 내용(이미지 파일)을 화면에 출력
			
			$(".boardImg").eq(num).children("img").attr("src", e.target.result);
			//  e.target.result : 파일 읽기 동작을 성공한 요소가 읽어들인 파일 내용
			}
			
			$("#brokerImg").css("width","200px").css("height","300px");
			$("#brokerImg").addClass("added");
			$(".c1").css("height","1500px");
        	 $(".c2").css("height","1420px");
			
      }
}

//멤버-----------------------------------------------------------------------

//회원가입 유효성 검사 객체
var validateCheck = {
	"userid" : false,
	"password" : false,
	"confirmpassword" : false,
	"email" : false,
	"nickname" : false,
	"usertel" : false
}


//멤버아이디 유효성 검사
//영어 대,소문자 + 숫자, 총 5-20글자
$("#userid").on("input", function(){
	var regExp = /^[a-zA-Z\d]{5,20}$/;
	
	var value = $("#userid").val(); 
	
	//일반 회원 아이디 형식 유효하지 않을 때
	if(!regExp.test(value)){
        $("#checkId").text("아이디 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.userid = false;
    }
	else{
	//아이디 형식 유효할때 ajax로 중복검사진행
		$.ajax({
			url:"idDupCheck.do",
			data: {"userid": value},
			type: "post",
			success: function(result){
				if(result == 0){ // 중복되지 않은 경우
                    $("#checkId").text("사용 가능한 아이디 입니다.").css("color","green");
                    validateCheck.userid = true;
               }else{
                    $("#checkId").text("이미 사용중인 아이디 입니다.").css("color","red");
                    validateCheck.userid = false;
               }
			},
			error: function(){
				console.log("아이디 중복 검사 실패");
			}
		});
		
	}
	
});

//공인 중개사 아이디 유효성 검사
$("#userid2").on("input", function(){
	var regExp = /^[a-zA-Z\d]{5,20}$/;
	
	var value = $("#userid2").val(); 
	
	//일반 회원 아이디 형식 유효하지 않을 때
	if(!regExp.test(value)){
        $("#checkId2").text("아이디 형식이 유효하지 않습니다.").css("color","red");
        validateCheck.userid = false;
    }
	else{
	//아이디 형식 유효할때 ajax로 중복검사진행
		$.ajax({
			url:"idDupCheck.do",
			data: {"userid": value},
			type: "post",
			success: function(result){
				if(result == 0){ // 중복되지 않은 경우
                    $("#checkId2").text("사용 가능한 아이디 입니다.").css("color","green");
                    validateCheck.userid = true;
               }else{
                    $("#checkId2").text("이미 사용중인 아이디 입니다.").css("color","red");
                    validateCheck.userid = false;
               }
			},
			error: function(){
				console.log("아이디 중복 검사 실패");
			}
		});
		
	}
	
});

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

	
//약관동의 검사
	//동의체크
	var agreeCheck = {
		"agree1" : false,
		"agree2" : false,
		"agree3" : false
	}
	
	if($("#agreeCheckbox").is(":checked")){
		agreeCheck.agree1=true ;
	}
	
	if($("#useTermsCheckbox").is(":checked")){
		agreeCheck.agree2=true ;
	}
	
	if($("#ageAgreeCheckbox").is(":checked")){
		agreeCheck.agree3=true ;
	}
	


	for(var a in agreeCheck){
		if(!agreeCheck[a]){
			var msg1;
			switch(a){
				case "agree1": msg1 = "개인정보 수집 이용동의"; break;
                case "agree2": msg1 = "my room 이용약관 "; break;
                case "agree3": msg1 = "만 14세 미만 가입 제한"; break;
			}
			swal(msg1 + "에 동의해주세요.");
			return false; 
		}
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

	//공인중개사 사진 
	if(!$("#brokerImg").hasClass("added")){
		swal("공인중개사 자격증 사진을 올려주세요.");
		return false;
	}
	
//약관동의 검사
	//동의체크
	var agreeCheck = {
		"agree1" : false,
		"agree2" : false,
		"agree3" : false
	}
	
	if($("#agreeCheckbox1").is(":checked")){
		agreeCheck.agree1=true ;
	}
	
	if($("#useTermsCheckbox1").is(":checked")){
		agreeCheck.agree2=true ;
	}
	
	if($("#ageAgreeCheckbox1").is(":checked")){
		agreeCheck.agree3=true ;
	}
	


	for(var a in agreeCheck){
		if(!agreeCheck[a]){
			var msg1;
			switch(a){
				case "agree1": msg1 = "개인정보 수집 이용동의"; break;
                case "agree2": msg1 = "my room 이용약관 "; break;
                case "agree3": msg1 = "만 14세 미만 가입 제한"; break;
			}
			swal(msg1 + "에 동의해주세요.");
			return false; 
		}
	}
	
}



