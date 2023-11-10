package com.groupfour.foodbox.mapper.user;

import com.groupfour.foodbox.domain.BookmarkDTO;
import com.groupfour.foodbox.domain.UserDTO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Mapper
public interface MypageMapper {

    UserDTO infoUpdateChk(String user_id);

    int pwUpdate(UserDTO userDTO);

    UserDTO infoList(String user_id);

    void infoUpdate(UserDTO userDTO);

    List<BookmarkDTO> bookmarkView(String id);

    void bookmarkDel(Long bm_recipe_id);

    void userDelete(int user_no);
}
