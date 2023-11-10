package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.UserDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserLoginService {
    //유저정보 가져오기
    UserDTO getUser(String user_id);
    // 로그인하기
    boolean userLogin(UserDTO userDto, HttpServletRequest req);

    // 아이디 찾기
    String findId(String name, String email);

    // 비밀번호 찾기
    int findPw(String uid, String uEmail);
}
