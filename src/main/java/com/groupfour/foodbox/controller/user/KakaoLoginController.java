package com.groupfour.foodbox.controller.user;

import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.service.user.KakaoLoginService;
import com.groupfour.foodbox.service.user.UserLoginService;
import com.groupfour.foodbox.service.user.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class KakaoLoginController {

    @Autowired
   private KakaoLoginService kakaoLoginService;

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserRegisterService userRegisterService;



    @GetMapping("/oauth/kakao")
    public String kakaoCallback(String code, HttpSession session) {
        // 1. 인증 서버로 부터 받은 인가 CODE
        System.out.println("카카오로 부터 받은 code = " + code);
        //return "카카오 인가코드 : " + code;

    // 2. 액세스 토큰 가져오기
    String accessToken = kakaoLoginService.getAccessToken(code);
        System.out.println("accessToken = "+accessToken);

    // 3. 사용자 정보 가져오기
//        String userInfo = kakaoLoginService.getUserInfo(accessToken);
    UserDTO kakaoUser = kakaoLoginService.getUserInfo(accessToken);

    // 4. 회원 가입
    // 이미 가입된 회원인지 확인하기
    UserDTO findUser = userLoginService.getUser(kakaoUser.getUser_id());

        if(findUser.getUser_id()==null)

    {

        userRegisterService.userRegister(kakaoUser);

        findUser = userLoginService.getUser(kakaoUser.getUser_id());
    }

    // 5. 인증 처리
        System.out.println("findUser : "+findUser.getUser_id());
        session.setAttribute("userLoginDto",findUser);

        return"redirect:/";
}

}
