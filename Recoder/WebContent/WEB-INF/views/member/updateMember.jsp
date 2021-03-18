<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>html문서 제목</title>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>

    <style>
        .col-md-3 {
            float: left;
        }

        .btn_area {
            text-align: center;
        }

        table {
            border-top: 3px solid gray;
            border-collapse: collapse;
            margin: 0 auto;
            width: 60%;
        }

        td,
        tr {
            border-bottom: 1px solid lightgray;
            padding: 10px;
        }

        #td1 {
            background-color: rgb(243, 243, 243);
            text-align: center;
            width: 30%;
        }

        #title {
            text-align: center;
        }

        form,
        div,
        input,
        #phone1 {
            color: rgb(87, 84, 84);
        }

        .input_control {
            border: 1px solid #ced4da;
            border-radius: .25rem;
            height: calc(1.5em + .75rem + 2px);
        }

        #pw1,
        #pw2 {
            text-align: center;
        }

        label {
            width: 100px;
        }

        .tel {
            padding-left: 0px;
        }
    </style>
</head>

<body>
	 <!-- WEB-INF/views/common/header.jsp 여기에 삽입(포함) -->
<jsp:include page="../common/header.jsp"></jsp:include>
    <div class="section1">
    <c:set var="phone" value="${fn:split(loginMember.memTel, '-') }"/>
        <div id="title">
            <h1>내 정보 수정</h1>
        </div><br>
        <form method="POST" action="updateMemberServlet.do" accept-charset="utf-8" onsubmit="return validate();">
            <table>
                <tr>
                    <td id="td1">아이디</td>
                    <td><input type="text" readonly id="userId" name="userId" value="${loginMember.memId }" class="input_control userId"></td>
                </tr>
                <tr>
                    <td id="td1">비밀번호</td>
                    <td><input type="password" placeholder="비밀번호 입력" id="pw1" name="pw1" class="input_control pw"><span id="checkPwd1"></span></td>
                    
                </tr>
                <tr>
                    <td id="td1">비밀번호 확인</td>
                    <td><input type="password" id="pw2" placeholder="비밀번호 확인" name="pw2" class="input_control pw"><span id="checkPwd2"></span></td>
                </tr>
                <tr>
                    <td id="td1">닉네임</td>
                    <td><input type="text" id="userNickname" name="userNickname" value="${loginMember.memNick }" class="input_control nickname">&nbsp &nbsp&nbsp&nbsp
                            <span id="checkNick"></span>
                    </td>
                </tr>
                <tr>
                    <td id="td1">이메일</td>
                    <td><input type="email" id="userEmail" name="userEmail" value="${loginMember.memEmail }" class="input_control email"><span id="checkEmail"></span></td>
                </tr>
                
                <tr>
                    <td id="td1">전화번호</td>
                    <td>
                        <div class="col-md-3 tel">
                            <select class="custom-select" id="phone1" name="phone1">
                                <option>010</option>
                                <option>011</option>
                                <option>016</option>
                                <option>017</option>
                            </select>
                        </div>
 
                        <div class="col-md-3 tel">
                            <input type="number" class="form-control phone" id="phone2" name="phone2"
                                value="${phone[1]}">
                        </div>

                        <div class="col-md-3 tel">
                            <input type="number" class="form-control phone" id="phone3" name="phone3"
                                value="${phone[2]}">
                        </div>
                        <div>
                        	<span id="checkPhone">&nbsp;</span>
                        </div>
                    </td>
                </tr>
            </table><br><Br>
            <div class="btn_area">
                <button type="submit" class="btn btn-primary">확인</button>
                <button type="button" class="btn btn-primary">취소</button>
            </div>
        </form>
    </div>
    <script src="${contextPath}/resources/js/memberUpdate.js"></script>
</body>

</html>