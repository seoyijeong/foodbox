<!-- 이메일 인증-->


let serverUUID = "";
let isEmailChk = false;

function emailCheck_two(){
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
                $("#confirmEmail").html('<div class="email_inner">'
                    // +'<span class="must_write03">이메일 인증</span>'
                    + '<div class="label_box">'
                    +'<label for="user_email" class="email_confirm_label">이메일 인증</label>'
                    // +'<span class="must_write04">&ast;</span>'
                    +'</div>'
                    +'<div class="input_box">'
                    +'<div class="content_box">'
                    // +'<input class="register_content_box" type="text" id="confirmUUID" maxLength="16" placeholder="인증번호를 입력해주세요."/>'
                    +' <input type="text" id="confirmUUID" class="update_input" maxLength="16" placeholder="인증번호를 입력해주세요"/>'
                    +'</div>'
                    +'<p class="chkMsg"></p>'
                    +'</div>'
                    +'</div>'
                    // +'<div class="register_content03">'
                    +'<div class="email_chk_box">'
                    // +'<div class="content_button">'
                    // +'<input class="register_content_button" type="button" onclick="emailConfirm_two()" value="인증확인"/>'
                    +'<input class="email_check" onclick="emailConfirm_two()" value="인증확인" />'
                    // +'</div>'
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

function emailConfirm_two(){
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