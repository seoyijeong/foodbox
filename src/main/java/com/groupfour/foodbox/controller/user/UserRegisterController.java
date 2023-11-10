package com.groupfour.foodbox.controller.user;

import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.service.user.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class UserRegisterController {

    @Autowired
    JavaMailSender emailSender;

    @Autowired
    private UserRegisterService userRegisterService;

    //비밀번호 암호화
//    @Autowired
  //  private PasswordEncoder passwordEncoder;

    // 회원가입 페이지로 이동
    @GetMapping("/userRegister")
    public String userRegister(UserDTO userDto) {

        return "/user/userRegister";
    }

    // 회원가입
    @PostMapping("/userRegister")
    public String userRegisterPost(UserDTO userDto) {
        System.out.println("userDto.getUser_id() = " + userDto.getUser_id());
        System.out.println("userDto.getUser_birthday() = " + userDto.getUser_birthday());
//        if(userDto.getUser_birthday().equals("") || userDto.getUser_birthday() == null) {
//            userDto.setUser_birthday();
//        }
        int n = userRegisterService.userRegister(userDto);

        return "redirect:/user/userLogin";
    }

    // 아이디 중복체크
    @PostMapping("/checkUser_id")
    @ResponseBody
    public String checkUser_id(@RequestParam("user_id") String user_id) {
        System.out.println("user_id = " + user_id);
        //db에 회원이 입력한 user_id가 있는지 조회
        UserDTO userDto = userRegisterService.checkUser_id(user_id);
        System.out.println("userDto: " + userDto);
//        if(userDto !=null || "".equals(user_id.trim())){
        if (userDto != null) {
            return "no";
        }
        return "yes";
    }

    // 이메일 인증
    @GetMapping("/emailConfirm")
    @ResponseBody
    public String emailConfirm(@RequestParam("uEmail") String uEmail) throws Exception {
        String confirm = userRegisterService.emailCheck(uEmail);
        return confirm;
    }
}
