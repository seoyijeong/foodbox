package com.groupfour.foodbox.controller.user;

import com.groupfour.foodbox.domain.BookmarkDTO;
import com.groupfour.foodbox.domain.RecipeDTO;
import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.service.user.MypageService;
import com.groupfour.foodbox.service.user.RecipePageService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("/user")
public class MyPageController {

    @Autowired
    MypageService mypageService;

    @Autowired
    RecipePageService recipePageService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 비밀번호 재확인
    @GetMapping("/user_pwUpdateChk")
    public String infoUpdateChkView(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("userLoginDto");
        model.addAttribute("userDTO", mypageService.infoUpdateChk(userDTO.getUser_id()));

//        System.out.println("DB 비밀번호 : " + mypageService.infoUpdateChk(userDTO.getUser_id()));
        return "user/user_pwUpdateChk";
    }

    // 비밀번호 재확인 후 비밀번호 변경 페이지 이동
    @PostMapping("/user_pwUpdateChk")
    public String pwUpdateCheck(String user_id, String user_pw, RedirectAttributes rttr) {
        UserDTO userDTO = mypageService.infoUpdateChk(user_id);
        String db_pw = userDTO.getUser_pw();
        if(passwordEncoder.matches(user_pw, db_pw)) {
            return "redirect:/user/user_pwModify";
        } else {
            rttr.addAttribute("msg", "비밀번호를 다시 확인해 주세요");
            return "redirect:/user/user_pwUpdateChk";
        }
    }

    @GetMapping("/user_pwModify")
    public String pwUpdateView() {

        return "user/user_pwUpdate";
    }

    // 비밀번호 수정
    @PostMapping("/user_pwModify")
    @ResponseBody
    public int pwModify(@RequestBody UserDTO userDTO) {
        int new_pw = mypageService.pwModify(userDTO);
        return new_pw;
    }

    // 새 비밀번호 변경 후 페이지 이동
    @GetMapping("/user_pwUpdateChkPage")
    public String pwModifyOk() {
        return "redirect:/user/user_pwUpdateChk";
    }

    // 회원정보 수정 view
    @GetMapping("/user_infoUpdateView")
    public String infoUpdateView(HttpSession session, Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("userLoginDto");
        model.addAttribute("userDTO", mypageService.infoList(userDTO.getUser_id()));
        return "user/user_infoUpdateView";
    }

    // 회원정보 수정
    @PostMapping("/user_infoUpdateView")
    public String infoModify(UserDTO userDTO) {
        mypageService.infoModify(userDTO);
//        System.out.println("회원 정보 수정 controller = " + userDTO);
        return "redirect:/user/user_infoUpdateView";
    }

    // 회원 삭제
    @PostMapping("/user_deleteUser")
    public @ResponseBody void deleteUser(@RequestBody UserDTO userDTO, HttpSession session) {
        session.invalidate();
        int user_no = userDTO.getUser_no();
        mypageService.userDelete(user_no);
    }

    // 북마크 리스트
    @GetMapping("/user_bookmarkView")
    public String bookmarkListView(HttpSession session, Model model) {
       UserDTO userDTO = (UserDTO) session.getAttribute("userLoginDto");
       String id = userDTO.getUser_id();
       List<BookmarkDTO> bookmarkList = mypageService.bookmarkView(id);
       model.addAttribute("bookmarkList", bookmarkList);
        return "user/bookmarkList";
    }

    // 북마크 삭제
    @PostMapping("/user_bookmarkDel")
    @ResponseBody
    public void deleteBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
        Long bm_recipe_id = bookmarkDTO.getBm_recipe_id();
        mypageService.bookmarkDelete(bm_recipe_id);
    }
}
