package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.mapper.admin.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 회원리스트
    @Override
    public List<UserDTO> userList(String keyword, String searchType, PageDTO pageDTO) {
        List<UserDTO> userList = userMapper.userList(keyword,searchType, pageDTO);
        return userList;
    }

    @Override
    public int userCount(String keyword, String searchType) {
        int userCount = userMapper.userCount(keyword,searchType);
        return userCount;
    }


    // 회원 삭제
    @Override
    public int userDelete(int user_no){
        int n = userMapper.userDelete(user_no);
        return n;
    }

    // 회원 선택 삭제
    @Override
    public int usersDelete(List<String> chkList) {
        int n = userMapper.usersDelete(chkList);
        return n;
    }

    // 회원 검색
    @Override
    public List<UserDTO> userSearch(String user_name, String user_gender) {
        List<UserDTO> list = userMapper.userSearch(user_name, user_gender);
        return list;
    }

    // 회원 정보
    @Override
    public UserDTO userInfo(int user_no){
        return userMapper.userInfo(user_no);
    }

//    // 회원 수정
//    @Override
//    public void userModify(UserDTO userDto){
//        userMapper.userModify(userDto);
//    }

    // 회원 수정
    @Override
    public void userModify(UserDTO userDto){
        userDto.setUser_pw(passwordEncoder.encode(userDto.getUser_pw()));
        userMapper.userModify(userDto);
    }

}
