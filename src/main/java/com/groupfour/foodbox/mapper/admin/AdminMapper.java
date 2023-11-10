package com.groupfour.foodbox.mapper.admin;

import com.groupfour.foodbox.domain.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    AdminDTO adminLogin(AdminDTO dto);
}
