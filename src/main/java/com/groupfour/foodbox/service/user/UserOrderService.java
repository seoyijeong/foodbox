package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.dto.UserOrderCheckDTO;
import com.groupfour.foodbox.dto.UserOrderDTO;
import com.groupfour.foodbox.dto.UserOrderDetailDTO;

import java.util.List;

public interface UserOrderService {
    List<UserOrderCheckDTO> userOrder(String userId);

    UserDTO userOrderInfo(String userId);

    int addOrderList(UserOrderCheckDTO userOrderCheckDTO);

    void addFastPay(UserOrderCheckDTO userOrderCheckDTO);

    List<UserOrderDTO> getUserOrderList(String id);

    List<UserOrderDetailDTO> getUserOrderDetail(int order_no);

    void userOrderDelete(int orderNo, List<UserOrderDetailDTO> orderDetail);

    UserOrderCheckDTO getProdInfo(int prodCode);

    void deleteCart(String userId);
}
