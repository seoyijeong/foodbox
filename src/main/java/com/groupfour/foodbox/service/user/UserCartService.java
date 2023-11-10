package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.dto.UserCartDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserCartService {

    void insertCart(UserCartDTO userCartDTO);

    UserCartDTO checkCart(String id, int prod_code);

    void modifyQty(int order_qty, int cart_no);

    List<UserCartDTO> userCartList(String user_id);

    void userCartDelete(int cartNo);
}
