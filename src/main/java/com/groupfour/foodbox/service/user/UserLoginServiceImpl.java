package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.mapper.user.UserLoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    UserLoginMapper userLoginMapper;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    PasswordEncoder passwordEncoder;

    //유저정보 가져오기(카카오로그인)
    public UserDTO getUser(String user_id) {
        // 익명클래스를 사용하면 가독성이 떨어져서 람다식으로 치환해서 사용한다.
//        User findUser = userRepository.findByUsername(username).orElseGet(new Supplier<User>() {
//            @Override
//            public User get() {
//                return new User();
//            }
//        });

        // 검색결과가 없으면 빈 객체를 리턴한다.
        UserDTO findUser = userLoginMapper.findByUserId(user_id).orElseGet(()->{
            return new UserDTO();
        });

        return findUser;

    }

    // 로그인 하기
    @Override
    public boolean userLogin(UserDTO userDto, HttpServletRequest req) {
        HttpSession session = req.getSession();

        // 아이디와 일치하는 회원정보를 DTO에 담아서 가져옴
        UserDTO userLoginDto = userLoginMapper.userLogin(userDto);

        if(userLoginDto != null){ // 일치하는 아이디가 있는 경우
            String inputPw= userDto.getUser_pw(); // 사용자가 입력한 비번
            String dbPw = userLoginDto.getUser_pw(); // DB 비번

            if(true ==passwordEncoder.matches(inputPw,dbPw)){ // 비번 일치
                session.setAttribute("userLoginDto", userLoginDto);
                System.out.println("로그인 성공");
                return true;
            }else{ // 비번 불일치
                System.out.println("로그인 실패");
                return false;
            }
        }
        return false;
    }

    // 아이디 찾기
    @Override
    public String findId(String name, String email) {
        String resultId= userLoginMapper.findId(name, email);

        return resultId;
    }

    // 비밀번호 찾기
    @Override
    public int findPw(String uid, String uEmail) {
        // 임시비밀번호
        String tempPw = UUID.randomUUID().toString().substring(0,6);
        MimeMessage mail = mailSender.createMimeMessage();

        String mailContents = "<h3>임시 비밀번호 발급</h3></br>"
                +"<h2>"+tempPw+"</h2>"
                +"<p>로그인 후 마이페이지에서 비밀번호를 변경해주면 됩니다.</p>";

        try {
            mail.setSubject("푸드박스 [임시 비밀번호]", "utf-8");
            mail.setText(mailContents, "utf-8", "html");

            // 상대방 메일 셋팅
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(uEmail));

            mailSender.send(mail);

        } catch (Exception e) {
            e.printStackTrace();
        }
        tempPw = passwordEncoder.encode(tempPw);
        int n = userLoginMapper.findPw(uid, uEmail, tempPw);

        return n;
    }


}

