package com.groupfour.foodbox.mapper.user;

import com.groupfour.foodbox.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserLoginMapper {

    // 로그인
     UserDTO userLogin(UserDTO userDto);

    // 아이디 찾기
    String findId(String name, String email);

    // 비밀번호 찾기
    int findPw(String uid, String uEmail, String tempPw);

    Optional<UserDTO> findByUserId(String user_id);
}
