package com.groupfour.foodbox.mapper.user;

import com.groupfour.foodbox.dto.UserCartDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserCartMapper {
    void insertCart(UserCartDTO userCartDTO);
    UserCartDTO checkCart(String user_id, int prod_code);

    void modifyQty(int order_qty, int cart_no);

    List<UserCartDTO> userCartList(String user_id);

    void userCartDelete(int cartNo);
}
