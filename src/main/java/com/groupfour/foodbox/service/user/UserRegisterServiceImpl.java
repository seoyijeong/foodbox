package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.mapper.user.UserRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.UUID;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @Autowired
    private JavaMailSender mailSender;
    //비밀번호 암호화
    @Autowired
    private PasswordEncoder passwordEncoder;


    // 회원 가입
    @Override
    public int userRegister(UserDTO userDto) {
        System.out.println("userDto = " + userDto);
        userDto.setUser_pw(passwordEncoder.encode(userDto.getUser_pw()));
        int n = userRegisterMapper.userRegister(userDto);
        return n;
    }

    // 아이디 중복체크
    @Override
    public UserDTO checkUser_id(String user_id) {
        return userRegisterMapper.checkUser_id(user_id);
    }


    // 이메일 인중 NEW
    public String emailCheck(String uEmail)throws Exception {
        System.out.println("service :: uEmail = " + uEmail);

        // 인증코드 생성
        String uuid = UUID.randomUUID().toString().substring(0, 6);
        System.out.println("uuid = " + uuid);

        MimeMessage mail = mailSender.createMimeMessage();

        String mailContents = "<h3>안녕하세요 푸드박스입니다.</h3></br>" +
                "<span>아래 코드를 이메일 인증 창에 입력해주세요</span>"
                +"<h2>"+uuid+"</h2>";

        try {
            mail.setSubject("푸드박스 [이메일 인증]", "utf-8");
            mail.setText(mailContents, "utf-8", "html");

            // 상대방 메일 셋팅
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(uEmail));

            mailSender.send(mail);
            return uuid;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "fail";
    }

}