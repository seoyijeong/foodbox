package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.BookmarkDTO;
import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.mapper.user.MypageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class MypageServiceImpl implements MypageService{

    @Autowired
    MypageMapper mypageMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO infoUpdateChk(String user_id) {
//        System.out.println("mypageMapper.infoUpdateChk(user_id) = " + mypageMapper.infoUpdateChk(user_id));
        return mypageMapper.infoUpdateChk(user_id);
    }

    @Override
    public int pwModify(UserDTO userDTO) {
        userDTO.setNew_pw(passwordEncoder.encode(userDTO.getNew_pw()));

        return mypageMapper.pwUpdate(userDTO);
    }

    @Override
    public UserDTO infoList(String user_id) {
       UserDTO userDTO = mypageMapper.infoList(user_id);
       return userDTO;
    }

    @Override
    public void infoModify(UserDTO userDTO) {
        mypageMapper.infoUpdate(userDTO);
//        System.out.println("회원정보수정 mapper = " + userDTO);
    }

    @Override
    public List<BookmarkDTO> bookmarkView(String id) {
        List<BookmarkDTO> bookmarkList = mypageMapper.bookmarkView(id);
        return bookmarkList;
    }

    @Override
    public void bookmarkDelete(Long bm_recipe_id) {
        mypageMapper.bookmarkDel(bm_recipe_id);
    }

    @Override
    public void userDelete(int user_no) {
        mypageMapper.userDelete(user_no);
    }


}
