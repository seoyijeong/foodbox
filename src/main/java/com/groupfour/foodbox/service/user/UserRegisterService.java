package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.UserDTO;
import org.springframework.stereotype.Service;

//import javax.mail.internet.MimeMessage;

@Service
public interface UserRegisterService {

    // 회원 가입
    int userRegister(UserDTO userDto);

    // 아이디 중복체크
    UserDTO checkUser_id(String user_id);

    // 이메일 인증
    String emailCheck(String user_email)throws Exception;

}