package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.BookmarkDTO;
import com.groupfour.foodbox.domain.UserDTO;

import java.util.List;

public interface MypageService {

    UserDTO infoUpdateChk(String user_id);

    int pwModify(UserDTO userDTO);

    UserDTO infoList(String user_id);

    void infoModify(UserDTO userDTO);

    List<BookmarkDTO> bookmarkView(String id);

    void bookmarkDelete(Long bm_recipe_id);

    void userDelete(int user_no);
}
