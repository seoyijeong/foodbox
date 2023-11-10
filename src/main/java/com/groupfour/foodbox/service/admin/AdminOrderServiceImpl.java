package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.dto.AdminOrderDTO;
import com.groupfour.foodbox.dto.AdminOrderDetailDTO;
import com.groupfour.foodbox.dto.OrderStatus;
import com.groupfour.foodbox.dto.UserOrderDetailDTO;
import com.groupfour.foodbox.mapper.admin.AdminOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    AdminOrderMapper adminOrderMapper;

    @Override
    public int orderListCount() {
        int orderListCount = adminOrderMapper.orderListCount();
        return orderListCount;
    }

    @Override
    public List<AdminOrderDTO> orderList(PageDTO pageDTO) {
        List<AdminOrderDTO> orderList = adminOrderMapper.orderList(pageDTO);

        return orderList;
    }

    @Override
    public int orderDetailCount(int orderNo) {
        int orderDetailCount = adminOrderMapper.orderDetailCount(orderNo);
        return orderDetailCount;
    }

    @Override
    public List<AdminOrderDetailDTO> orderDetail(int orderNo, PageDTO pageDTO) {
        List<AdminOrderDetailDTO> orderDetail = adminOrderMapper.orderDetail(orderNo, pageDTO);

        for(AdminOrderDetailDTO dto : orderDetail) {
            dto.setTotPrice(dto.getTotPrice());
        }

        return orderDetail;
    }

    @Override
    public List<AdminOrderDetailDTO> getOrderDetail(int orderNo) {
        return adminOrderMapper.getOrderDetail(orderNo);
    }

    @Override
    public void adminOrderDelete(int orderNo) {
        List<AdminOrderDetailDTO> orderDetailList = adminOrderMapper.getOrderDetail(orderNo);

        for(AdminOrderDetailDTO dto: orderDetailList) {
            if(dto.getOrder_status() == (OrderStatus.ORDERSUCCESS)) {
                adminOrderMapper.adminOrderDelete(orderNo);
                adminOrderMapper.adminOrderListDelete(orderNo);
                dto.setProd_qty(dto.getProd_qty() + dto.getOrder_qty());
                int prod_code = dto.getProd_code();
                int prod_qty = dto.getProd_qty();
                adminOrderMapper.deleteOrder_qty(prod_code, prod_qty);
            }
        }
    }

}
