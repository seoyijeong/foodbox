package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.AdminDTO;
import com.groupfour.foodbox.mapper.admin.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;


    @Override
    public boolean adminLogin(AdminDTO dto, HttpServletRequest req) {
        HttpSession session = req.getSession();
        //아이디와 일치하는 회원정보를 DTO에 담아서 가져옴
        AdminDTO adminLoginDTO = adminMapper.adminLogin(dto);

        if (adminLoginDTO != null) { //일치하는 아이디가 있는 경우
            //사용자가 입력한 비번과 db 비번이 일치하는지
            String inputPw = dto.getAdmin_pw();//사용자가 입력한 비번
            String dbPw = adminLoginDTO.getAdmin_pw(); //db에서 불러온 비번

            if (inputPw.equals(dbPw)) { //비번일치
                session.setAttribute("adminLoginDTO", adminLoginDTO);   //비번 일치시 세션에 저장
                return true;
            } else {  //비번 불일치
                return false;
            }
        }
        return false;
    }
}