// 아이디 유효성 검사

let idChk = false;
function inputId(){

    // 아이디 6글자 이상 16이하, 영어/숫자만 가능
    let regId = /^[a-zA-Z0-9]{6,16}$/;
    let id = $("#user_id").val();

    if(id == "") {
        $("#idMsg").text("아이디를 입력해주세요.");
        idChk = false;
        return;
    } else if (false === regId.test(id)) {
        $("#idMsg").text('6자이상~16자이하, 영문/숫자만 사용해주세요.');
        idChk = false;
        return;
    } else {
        $('#idMsg').text("  ");
        idChk = true;
    }
}


// 비밀번호 유효성 검사

let pwChk = false;
function inputPw(){

    // 비밀번호 10글자 이상 20글자 이내, 영어/숫자/특수문자 포함
    let regPw = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,20}$/;
    let pw = $("#user_pw").val();


    if(pw == "") {
        $("#pwMsg").text("비밀번호를 입력해주세요.");
        pwChk = false;
        return;
    } else if (false === regPw.test(pw)) {
        $("#pwMsg").text('10자이상~20자이하, 영문/숫자/특수문자를 포함하여 사용해주세요.');
        pwChk = false;
        return;
    } else if (true === regPw.test(pw)){
        $('#pwMsg').text("  ");
        pwChk = true;
    }

    else {
        $('#pwMsg').text("  ");
        pwChk = true;
    }
}

// 비밀번호 확인 유효성 검사

let pwConfirm = false;

function inputPwChk() {

    let PwChk = $("#user_pw").val();

    if ($("#user_pw2").val() != PwChk) {
        $("#checkPwMsg").text("동일한 비밀번호를 입력해주세요");
        pwConfirm = false;
        return;
    } else {
        $("#checkPwMsg").text("비밀번호가 일치합니다");
        $("#checkPwMsg").css({"color": "blue", "font-size": "13px"});
        pwConfirm = true;
        return;
    }
}

// 이름 유효성 검사

let nameChk = false;

function inputName() {

    // 특수문자,영어,숫자는 사용불가 한글만 입력가능
    let regName = /^[가-힣]{2,15}$/;
    let name = $("#user_name").val();
    console.log(name);

    if (name == "") {
        $("#nameMsg").text("이름을 입력해주세요.");
        nameChk = false;
        return;
    } else if (false === regName.test(name)) {
        $("#nameMsg").text('2자이상~15자이하의 한글만 입력해주세요.');
        nameChk = false;
        return;
    } else {
        $('#nameMsg').text("  ");
        nameChk = true;
    }
}

// 이메일 유효성 검사

let emailChk = false;

function inputEmail() {

    // 이메일 영문자/숫자/_/./- @ 영문자/숫자/- . 영문자/숫자/-
    let regEmail = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+$/;
    let email = $("#user_email").val();


    if (email == "") {
        $("#emailMsg").text("이메일을 입력해주세요.");
        emailChk = false;
        return;
    } else if (false === regEmail.test(email)) {
        $("#emailMsg").text('올바른 이메일 형식이 아닙니다.');
        emailChk = false;
        return;
    } else {
        $('#emailMsg').text("  ");
        emailChk = true;
    }
}

// // 주소 유효성 검사
//
let postCodeChk = false;
//
// function inputPostCode() {
//
//
//     // 우편번호 숫자만 5개
//     // let regPostCode = /\d{5}/;
//     let postCode = $("#sample4_postcode").val();
//     alert(postCode);
//     if (postCode != "") {
//         postCodeChk = true;
//     }
//
//
//
//     // if (postCode == "") {
//     //     $("#postCodeMsg").text("우편번호를 입력해주세요.");
//     //     postCodeChk = false;
//     //     return;
//     // } else
//     //     if (false === regPostCode.test(postCode)) {
//     //     $("#postCodeMsg").text('올바른 우편번호가 아닙니다.');
//     //     postCodeChk = false;
//     //     return;
//     // } else {
//     //     // $('#postCodeMsg').text("  ");
//     //     postCodeChk = true;
//     // }
// }



// 전화번호 유효성 검사


let telChk = false;

function inputTel() {


    let regTel = /^(010|011|016|017|018|019)-[^0][0-9]{3,4}-[0-9]{4}$/;
    let tel = $("#user_tel").val();


    if (tel == "") {
        $("#telMsg").text("전화번호를 입력해주세요.");
        telChk = false;
        return;
    } else if (false === regTel.test(tel)) {
        $("#telMsg").text('올바른 번호 형식이 아닙니다.');
        telChk = false;
        return;
    } else {
        $('#telMsg').text("  ");
        telChk = true;
    }
}

// 생년월일 유효성 검사

let birthDayChk = false;

function inputBirthDay() {


    let birthDay = $("#user_birthday").val();

    if (birthDay === "") {
        $("#birthDayMsg").html("생년월일을 입력해주세요.");
        $("#birthDayMsg").css({"color": "red", "font-size": "11px"});
        birthDayChk = false;
        return;

    } else {
        $("#birthDayMsg").text("");
        birthDayChk = true;
    }

}

<!--아이디 중복체크-->

let duplicateChk = false;
function checkUser_id(){
    let user_id = $('#user_id').val();

    $.ajax({
        url:"/user/checkUser_id",
        type: "post",
        data:{"user_id":user_id}, // 서버에 전송할 데이터
        success: function(responseData){
            // responseData = "yes" or "no", yes:사용가능 no:사용불가
            console.log(responseData)
            if(responseData == "yes"){

                let regId = /^[a-zA-Z0-9]{6,16}$/;
                let id = $("#user_id").val();

                if(id == "") {
                    alert("아이디를 입력해주세요.");
                    idChk = false;
                    duplicateChk = false;
                    return;

                } else if (false === regId.test(id)) {
                    alert("6자이상~16자이하, 영문/숫자만 사용해주세요.");
                    idChk = false;
                    return;

                } else{
                    alert("사용 가능한 아이디입니다.");
                    idChk=true;
                    duplicateChk=true;
                }

            }else{ //resultData가 No인 경우
                alert("이미 사용중인 아이디입니다.");
                $("#register_button").attr("type","button");
                $("#register_button").on("click",function (){
                    idChk=false;
                    return;
                })

            }
        },
        error : function(){
            alert("서버에러 입니다.");
        }
    });
}

<!-- 이메일 인증-->
let serverUUID = "";
let isEmailChk = false;
function emailCheck(){
    let uEmail = $("#user_email").val();

    $.ajax({
        url:"/user/emailConfirm",
        type: "get",
        data: {"uEmail":uEmail},
        success: function(ePw){
            if(ePw != "fail"){
                serverUUID = ePw;
                console.log("이메일 인증코드 : " + ePw);

                $('#confirmEmail').show();
                $("#confirmEmail").html('<div class="register_content01">'
                    +'<span class="must_write03">이메일 인증</span>'
                    +'<span class="must_write04">&ast;</span>'
                    +'</div>'
                    +'<div class="register_content02">'
                    +'<div class="content_box">'
                    +'<input class="register_content_box" type="text" id="confirmUUID" maxLength="16" placeholder="인증번호를 입력해주세요."/>'
                    +'<p class="chkMsg"></p>'
                    +'</div>'
                    +'</div>'
                    +'<div class="register_content03">'
                    +'<div class="content_button">'
                    +'<input class="register_content_button" type="button" onclick="emailConfirm()" value="인증확인"/>'
                    +'</div>'
                    +'</div>');

            }else{
                alert("이메일을 다시 확인하세요.");
                $("#user_email").select();
            }
        },
        error:function(){
            alert("인증에 실패했습니다.");
        }
    });
}

function emailConfirm(){
    let confirmUUID = $("#confirmUUID").val();

    if(confirmUUID == null || confirmUUID ==""){
        alert("인증 코드를 입력해주세요.");
        $("#confirmUUID").select();
        //
        isEmailChk=false;
    }else if(serverUUID == confirmUUID){
        alert("인증에 성공하였습니다.");
        isEmailChk = true;
    }else{
        alert("인증코드를 다시 확인해주세요.");
        $("#confirmUUID").select();
        //
        isEmailChk=false;
        return;
    }
}



function userJoin(){

    <!--idChk가 false 면 가입 안되고 리턴됨-->
    if(idChk==false){
        alert("아이디를 확인해주세요.");
        user_register_form.user_id.focus();
        return;
    }

    <!--중복체크가 false 면 가입 안되고 리턴됨-->
    if(duplicateChk==false){
        alert("아이디 중복체크를 해주세요.")
        return;
    }
console.log(pwChk);
    <!--pwChk가 false 면 가입 안되고 리턴됨-->
    if(pwChk==false){
        alert("비밀번호를 확인해주세요.");
        user_register_form.user_pw.focus();
        return;
    }

    <!--pwConfirm가 false 면 가입 안되고 리턴됨-->
    if(pwConfirm==false){
        alert("동일한 비밀번호를 입력해주세요");
        user_register_form.user_pw2.focus();
        return;
    }

    <!--nameChk가 false 면 가입 안되고 리턴됨-->
    if(nameChk==false){
        alert("이름을 확인해주세요.")
        user_register_form.user_name.focus();
        return;
    }

    <!--emailChk가 false 면 가입 안되고 리턴됨-->
    if(emailChk==false){
        alert("이메일을 확인해주세요.")
        user_register_form.user_email.focus();
        return;
    }

    <!--이메일 인증을 안하면 false 가 되서 가입 안되고 리턴됨-->
    if(isEmailChk==false){
        alert("이메일 인증을 해주세요.")
        return;
    }

    console.log(postCodeChk);
    // <!--주소를 입력 안하면 false 가 되서 가입 안되고 리턴됨-->
    let postCode = $("#sample4_postcode").val();
    if (postCode != "") {
        postCodeChk = true;
    }
    if(postCodeChk==false){
        alert("주소를 확인해주세요.")
        return;
    }

    <!--telChk가 false 면 가입 안되고 리턴됨-->
    if(telChk==false){
        alert("전화번호를 확인해주세요.")
        user_register_form.user_tel.focus();
        return;
    }

    // <!--birthdayChk가 false 면 가입 안되고 리턴됨-->
    if(birthDayChk==false){
        alert("생년월일을 확인해주세요.")
        return;
    }


    <!--이용약관 동의 안하면 false 여서 가입 안되고 리턴됨-->
    let ToU = document.getElementById('ToU');
    if(ToU.checked===false) {
        alert("이용약관 동의는 필수사항입니다");
        return;
    }

    <!--개인정보 처리방침 안하면 false 여서 가입 안되고 리턴됨-->
    let privacy = document.getElementById('privacy');
    if(privacy.checked===false) {
        alert("개인정보 처리방침 동의는 필수사항입니다");
        return;
    }

    document.user_register_form.submit();
}