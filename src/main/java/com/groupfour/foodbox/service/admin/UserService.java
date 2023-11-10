package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

//    // 회원 리스트
//    List<UserDTO> userList();

    // 회원 리스트
    List<UserDTO> userList(String keyword, String searchType, PageDTO pageDTO);

    // 회원 삭제
    int userDelete(int user_no);

    // 회원 선택 삭제
    int usersDelete(List<String> chkList);

    //회원 검색
    List<UserDTO> userSearch(String user_name, String user_gender);

    // 회원 정보
    UserDTO userInfo(int user_no);

    // 회원 수정
    void userModify(UserDTO userDto);


    int userCount(String keyword, String searchType);
}


