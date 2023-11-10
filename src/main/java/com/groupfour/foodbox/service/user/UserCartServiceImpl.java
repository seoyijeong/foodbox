package com.groupfour.foodbox.service.user;


import com.groupfour.foodbox.dto.UserCartDTO;
import com.groupfour.foodbox.mapper.user.UserCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCartServiceImpl implements UserCartService {

    @Autowired
    UserCartMapper userCartMapper;

    @Override
    public void insertCart(UserCartDTO userCartDTO) {
        List<UserCartDTO> userCartList = userCartMapper.userCartList(userCartDTO.getUser_id());

        userCartMapper.insertCart(userCartDTO);
    }

    @Override
    public UserCartDTO checkCart(String id, int prod_code) {
        return userCartMapper.checkCart(id, prod_code);
    }

    @Override
    public void modifyQty(int order_qty, int cart_no) {
        userCartMapper.modifyQty(order_qty, cart_no);
    }

    @Override
    public List<UserCartDTO> userCartList(String user_id) {

        List<UserCartDTO> userCartList = userCartMapper.userCartList(user_id);

        return userCartList;
    }

    @Override
    public void userCartDelete(int cartNo) {
        userCartMapper.userCartDelete(cartNo);
    }
}
