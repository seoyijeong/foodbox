package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.dto.AdminOrderDTO;
import com.groupfour.foodbox.dto.AdminOrderDetailDTO;

import java.util.List;

public interface AdminOrderService {

    int orderListCount();

    List<AdminOrderDTO> orderList(PageDTO pageDTO);

    int orderDetailCount(int orderNo);

    List<AdminOrderDetailDTO> orderDetail(int orderNo, PageDTO pageDTO);


    List<AdminOrderDetailDTO> getOrderDetail(int orderNo);

    void adminOrderDelete(int orderNo);
}
