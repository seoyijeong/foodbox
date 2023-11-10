package com.groupfour.foodbox.mapper.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.dto.AdminOrderDTO;
import com.groupfour.foodbox.dto.AdminOrderDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminOrderMapper {

    int orderListCount();

    List<AdminOrderDTO> orderList(PageDTO pageDTO);

    int orderDetailCount(int orderNo);

    List<AdminOrderDetailDTO> orderDetail(int orderNo, PageDTO pageDTO);


    List<AdminOrderDetailDTO> getOrderDetail(int orderNo);

    void adminOrderDelete(int orderNo);

    void adminOrderListDelete(int orderNo);

    int deleteOrder_qty(int prodCode, int prodQty);


}
