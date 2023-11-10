package com.groupfour.foodbox.controller.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.service.admin.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    // 회원리스트
    @RequestMapping("/userList")
    public String userList(@RequestParam(defaultValue = "ALL") String keyword,
                              @RequestParam(defaultValue="ALL") String searchType,
                              PageDTO pageDTO, Model model){
        int userCount = userService.userCount(keyword,searchType);
        pageDTO.setValue(userCount);
        model.addAttribute("keyword",keyword);
        model.addAttribute("searchType",searchType);
        model.addAttribute("pageDTO", pageDTO);

        List<UserDTO> userList = userService.userList(keyword,searchType, pageDTO);
        model.addAttribute("userList", userList);
        return "/admin/user/userList";
    }


    // 회원삭제
    @RequestMapping ("/userDelete")
    public String userDelete(int user_no){
        userService.userDelete(user_no);

        return "redirect:/admin/userList";
    }

    // 선택삭제
    @GetMapping("/usersDelete")
    public String usersDelete(@RequestParam("chkUno") List<String> chkList) {
        System.out.println("chkList = " + chkList);

        if(chkList != null) {
            userService.usersDelete(chkList);
        }
        return  "redirect:/admin/userList";
    }

    // 회원검색
    @PostMapping("/userSearch")
    public String userSearch(String user_name, String user_gender, Model model){
        System.out.println("user_name = " + user_name);
        System.out.println("user_gender = " + user_gender);
        List<UserDTO> userList = userService.userSearch(user_name, user_gender);
        System.out.println("userList = " + userList);
        model.addAttribute("userList", userList);

        return "/admin/user/userList";
    }

    // 회원 정보
    @GetMapping("/userInfo")
    public String userInfo(int user_no, Model model){
        UserDTO userDto = userService.userInfo(user_no);
        model.addAttribute("userDto", userDto);

        return "/admin/user/userInfo";
    }

    // 회원 수정
    @PostMapping("/userModify")
    public String userModify(UserDTO userDto){
        userService.userModify(userDto);

        return  "redirect:/admin/userList";
    }



}
