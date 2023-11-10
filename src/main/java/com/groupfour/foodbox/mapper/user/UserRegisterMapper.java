package com.groupfour.foodbox.mapper.user;

import com.groupfour.foodbox.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRegisterMapper {

    // 회원 가입
    int userRegister(UserDTO userDto);

    // 아이디 중복체크
    UserDTO checkUser_id(String user_id);

    // 아이디 찾기
    String findId(UserDTO userDto);
}