
// 마이페이지 클릭 이벤트
$(function (){
    $(".mypage .mypage__submenu").hide();
    $(".mypage").click(function (){
        $(".mypage .mypage__submenu").toggle();
    });
});

// 비밀번호 재확인 버튼 클릭 이벤트
function userInfoBtn() {
    //
    // let pwInput = $("#user_pw_input").val();
    // let pw_check = $("#pw_check").val();
    // if(pw_check != null) {
    //     alert(pw_check);
    // }
    // // let userDTO = $("#user_pw_db").val();
    //
    // // 비밀번호를 입력하지 않은 경우
    // if (!pwInput|| pwInput.trim() == "") {
    //     alert("현재 비밀번호를 입력해주세요");
    //     $("#user_pw_input").focus();
    //     return;
    // }
    // 입력한 비밀번호와 데이터에 저장된 비밀번호 불일치 여부
    // if (pwInput != userDTO) {
    //     alert("비밀번호가 일치하지 않습니다");
    //     $("#user_pw_input").focus();
    //     return;
    // }
    // 일치하면 비밀번호 변경 페이지로 이동
    document.userinfo_input.submit();

}

// 새 비밀번호 유효성 검사

let newPwChk = false;

function inputNewPw(){

    // 비밀번호 10글자 이상 20글자 이내, 영어/숫자/특수문자 포함
    let reg = /^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,20}$/;
    // let reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{10,20}$/;
    let newPw = $("#new_pw").val();

    if(newPw == "") {
        $("#newPwMsg").text("새 비밀번호를 입력해주세요");
        newPwChk = false;
        return;
    } else if (false === reg.test(newPw)) {
        $("#newPwMsg").text('최소 10글자 이상이어야하며, 영문/숫자/특수문자를 포함해야합니다');
        newPwChk = false;
        return;
    } else {
        $('#newPwMsg').text("  ");
        newPwChk = true;
    }

}


// 새 비밀번호 확인 유효성 검사

let pwCheck = false;

function inputNewPwChk() {
    let newPwChk = $("#new_pw_chk").val();

    if ($("#new_pw").val() != newPwChk || newPwChk.trim() == "") {
        $("#checkPwMsg").text("동일한 비밀번호를 입력해주세요");
        pwCheck = false;
        return;
    } else {
        $("#checkPwMsg").text("비밀번호가 일치합니다");
        $("#checkPwMsg").css({"color":"blue", "font-size":"13px"});
        pwCheck = true;
        return;
    }

}

// 새 비밀번호 변경

function pwModifyBtn(user_id) {
    let new_pw = document.getElementById('new_pw').value;
    let user = {user_id: user_id, new_pw: new_pw};

    if (newPwChk == false) {
        alert("최소 10글자 이상이어야하며, 영문/숫자/특수문자를 포함해야합니다");
        return;
    }

    $.ajax({
        type: "post",
        url: "/user/user_pwModify",
        data: JSON.stringify(user),
        contentType: "application/json; charset=utf-8",
        success: function () {
            alert("비밀번호가 변경되었습니다");
            location.href = "/user/user_pwUpdateChkPage";
        },
        error: function (error) {
            console.error("에러:", error);
        }
    });

}

// 북마크 삭제

function bookmarkDel(bm_recipe_id) {
    if(confirm("책갈피를 삭제하시겠습니까?") == true) {
        $.ajax({
            url: '/user/user_bookmarkDel',
            type: 'post',
            data: JSON.stringify({bm_recipe_id: bm_recipe_id}),
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert("책갈피가 삭제되었습니다");
            location.replace('/user/user_bookmarkView');
        }).fail(function (error) {
            alert("책갈피 삭제 에러");
        });
    }
    else {
        return;
    }
}

// 휴대폰 번호 입력시 자동 하이픈(-) 생성 이벤트
let hypenTel = (target) => {
    target.value = target.value
        .replace(/[^0-9]/g, '')
        .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
    // 휴대폰 유효성검사 함수 호출
    inputTel();
}

// 회원정보 수정 유효성 검사

// 이름

function nameInput(){

    let regName = /^[가-힣]+$/;
    let nameInput = $("#user_name").val();

    // 공백 체크
    if (!nameInput || nameInput.trim() == "") {
        $("#checkNameMsg").text("이름을 입력해주세요");
        return;
    }
    // 한글 체크
    if(false === regName.test(nameInput)) {
        $("#checkNameMsg").text("한글로 입력해주세요");
        return;
    } else {
        $("#checkNameMsg").text(" ");
    }
}

// 이메일

function emailInput() {
    let regEmail = /^[0-9a-z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    let emailInput = $("#user_email").val();

    if(false === regEmail.test(emailInput)) {
        $("#checkEmailMsg").text("이메일 형식에 맞게 입력해주세요");
        return;
    } else {
        $("#checkEmailMsg").text(" ");
    }
}


// 휴대폰

function telInput() {
    let regTel = /^\d{3}-\d{3,4}-\d{4}$/;
    let telInput =  $("#user_tel").val();

    if (false === regTel.test(telInput)) {
        $("#checkTelMsg").text("휴대폰 번호를 입력해주세요");
        return;
    } else {
        $("#checkTelMsg").text(" ");
    }
}

// 회원정보 수정 버튼 이벤트
function userUpdateBtn() {
    // if (nameChk == false || emailChk == false || telChk == false) {
    //     alert("다시 한번 확인해 주세요");
    //     return;
    // } else {
        alert("회원정보가 수정되었습니다");
        $("#userinfo_form").submit();
}

// 회원 삭제 버튼 이벤트
function userDeleteBtn (user_no) {
    if(confirm("정말 탈퇴하시겠습니까?") == true) {
        $.ajax({
            type: "post",
            url: "/user/user_deleteUser",
            data: JSON.stringify({user_no : user_no}),
            contentType: "application/json; charset=utf-8"
        }).done(function (response) {
            alert("이용해주셔서 감사합니다");
            location.replace('/');
        }).fail(function (error) {
            alert("회원 탈퇴 에러" + error);
        });
    } else {
        return;
    }
}

