package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.AdminDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public interface AdminService {
    boolean adminLogin(AdminDTO dto,HttpServletRequest req);
}
