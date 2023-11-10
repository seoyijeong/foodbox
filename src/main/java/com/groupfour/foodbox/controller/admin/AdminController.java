package com.groupfour.foodbox.controller.admin;

import com.groupfour.foodbox.domain.AdminDTO;
import com.groupfour.foodbox.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")   //관리자// 웹페이지 이동 주소 //http://localhost:8800/admin/adminLogin
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("")
    public String admin() {
        return "admin/admin_main";
    }

    @GetMapping("/adminLogin")
    public String adminCheck() {
        return "admin/adminLoginForm";
    }

    @PostMapping("/adminLogin")
    public String adminLogin(AdminDTO dto,HttpServletRequest req, RedirectAttributes rttr) {
        boolean result = adminService.adminLogin(dto, req);

        HttpSession session = req.getSession();
        if (!result) { //로그인 실패
            rttr.addAttribute("result", 0); //->실패시 다 시 로그인 폼으로이동
            return "redirect:/admin/adminLogin";  //redirect는 get방식
        }
        session.setAttribute("adminLoginDto",dto);

        //jsp는 직접 view로 넘기는게 아니기 때문에 redirect불가
        return "redirect:/admin";
    }
    @GetMapping("/adminLogout")
    public String adminLogout(HttpSession session) {
        // 로그인할때 만들어 두었던 세션 초기화
        session.invalidate();

        return "redirect:/admin";
    }



}
