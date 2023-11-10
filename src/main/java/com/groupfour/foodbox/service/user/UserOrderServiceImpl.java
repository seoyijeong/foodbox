package com.groupfour.foodbox.service.user;


import com.groupfour.foodbox.domain.UserDTO;
import com.groupfour.foodbox.dto.OrderStatus;
import com.groupfour.foodbox.dto.UserOrderCheckDTO;
import com.groupfour.foodbox.dto.UserOrderDTO;
import com.groupfour.foodbox.dto.UserOrderDetailDTO;
import com.groupfour.foodbox.mapper.user.UserOrderMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    UserOrderMapper userOrderMapper;

    @Override
    public List<UserOrderCheckDTO> userOrder(String userId) {
        List<UserOrderCheckDTO> list = userOrderMapper.userOrder(userId);

        for(UserOrderCheckDTO dto : list) {
            dto.setTotPrice(dto.getTotPrice());
        }

        return list;
    }

    @Override
    public UserDTO userOrderInfo(String userId) {

        UserDTO userDTO = userOrderMapper.userOrderInfo(userId);

        return userDTO;
    }

    @Override
    public int addOrderList(UserOrderCheckDTO userOrderCheckDTO) {
        UserOrderDTO userOrderDTO = new UserOrderDTO();

        List<UserOrderCheckDTO> orderList = userOrderCheckDTO.getOrderList();
        UserOrderCheckDTO checkDTO = userOrderCheckDTO.getUserOrderCheckDTO();

        int orderTotPrice = 0;
        for (UserOrderCheckDTO dto : orderList) {
            orderTotPrice += dto.getTotPrice();
        }
        userOrderDTO.setOrderTotPrice(orderTotPrice);

        userOrderDTO.setUser_id(checkDTO.getUser_id());
        userOrderDTO.setUser_name(checkDTO.getUser_name());
        userOrderDTO.setProd_name(checkDTO.getProd_name());
        userOrderDTO.setUser_zipcode(checkDTO.getUser_zipcode());
        userOrderDTO.setReceiver_name(checkDTO.getReceiver_name());
        userOrderDTO.setReceiver_tel(checkDTO.getReceiver_tel());
        userOrderDTO.setUser_roadaddr(checkDTO.getUser_roaddr());
        userOrderDTO.setUser_detailaddr(checkDTO.getUser_detailaddr());


        userOrderDTO.setOrder_status(OrderStatus.ORDERSUCCESS);
        int n = userOrderMapper.insertOrderList(userOrderDTO);

        String id = userOrderDTO.getUser_id();
        String name = userOrderDTO.getUser_name();
        String zipcode = userOrderDTO.getUser_zipcode();
        String roadaddr = userOrderDTO.getUser_roadaddr();
        String detailaddr = userOrderDTO.getUser_detailaddr();
        String receiver_name = userOrderDTO.getReceiver_name();
        String receiver_tel = userOrderDTO.getReceiver_tel();
        int generatedOrderNo = userOrderDTO.getOrder_no();

        for (UserOrderCheckDTO dto : orderList) {
            UserOrderDetailDTO userOrderDetailDTO = new UserOrderDetailDTO();
            userOrderDetailDTO.setOrder_no(generatedOrderNo);
            userOrderDetailDTO.setOrder_status(OrderStatus.ORDERSUCCESS);
            userOrderDetailDTO.setUser_id(id);
            userOrderDetailDTO.setUser_name(name);
            userOrderDetailDTO.setReceiver_name(receiver_name);
            userOrderDetailDTO.setReceiver_tel(receiver_tel);
            userOrderDetailDTO.setUser_zipcode(zipcode);
            userOrderDetailDTO.setUser_roadaddr(roadaddr);
            userOrderDetailDTO.setUser_detailaddr(detailaddr);
            userOrderDetailDTO.setProd_code(dto.getProd_code());
            userOrderDetailDTO.setProd_name(dto.getProd_name());
            userOrderDetailDTO.setProd_thumbnail(dto.getProd_thumbnail());
            userOrderDetailDTO.setProd_price(dto.getProd_price());
            userOrderDetailDTO.setOrder_qty(dto.getOrder_qty());
            userOrderDetailDTO.setProd_qty(dto.getProd_qty() - dto.getOrder_qty());

            userOrderMapper.insertOrderDetail(userOrderDetailDTO);
            userOrderMapper.updateProd_qty(userOrderDetailDTO);

        }
        return n;
    }

    @Override
    public void deleteCart(String userId) {
        userOrderMapper.deleteCart(userId);
    }

    @Override
    public void addFastPay(UserOrderCheckDTO userOrderCheckDTO) {

        UserOrderCheckDTO userFastPayDTO = userOrderCheckDTO.getUserFastPayDTO();
        UserOrderCheckDTO checkDTO = userOrderCheckDTO.getUserOrderCheckDTO();
        userFastPayDTO.setTotPrice(userFastPayDTO.getTotPrice());

        UserOrderDTO userOrderDTO = new UserOrderDTO();
        userOrderDTO.setUser_id(checkDTO.getUser_id());
        userOrderDTO.setUser_name(checkDTO.getUser_name());
        userOrderDTO.setProd_name(checkDTO.getProd_name());
        userOrderDTO.setUser_zipcode(checkDTO.getUser_zipcode());
        userOrderDTO.setReceiver_name(checkDTO.getReceiver_name());
        userOrderDTO.setReceiver_tel(checkDTO.getReceiver_tel());
        userOrderDTO.setUser_roadaddr(checkDTO.getUser_roaddr());
        userOrderDTO.setUser_detailaddr(checkDTO.getUser_detailaddr());
        userOrderDTO.setOrderTotPrice(userFastPayDTO.getTotPrice());
        userOrderDTO.setOrder_status(OrderStatus.ORDERSUCCESS);

        userOrderMapper.insertOrderList(userOrderDTO);

        String id = userOrderDTO.getUser_id();
        String name = userOrderDTO.getUser_name();
        String zipcode = userOrderDTO.getUser_zipcode();
        String roadaddr = userOrderDTO.getUser_roadaddr();
        String detailaddr = userOrderDTO.getUser_detailaddr();
        String receiver_name = userOrderDTO.getReceiver_name();
        String receiver_tel = userOrderDTO.getReceiver_tel();
        int generatedOrderNo = userOrderDTO.getOrder_no();

        UserOrderDetailDTO userOrderDetailDTO = new UserOrderDetailDTO();
        userOrderDetailDTO.setOrder_no(generatedOrderNo);
        userOrderDetailDTO.setOrder_status(OrderStatus.ORDERSUCCESS);
        userOrderDetailDTO.setUser_id(id);
        userOrderDetailDTO.setUser_name(name);
        userOrderDetailDTO.setReceiver_name(receiver_name);
        userOrderDetailDTO.setReceiver_tel(receiver_tel);
        userOrderDetailDTO.setUser_zipcode(zipcode);
        userOrderDetailDTO.setUser_roadaddr(roadaddr);
        userOrderDetailDTO.setUser_detailaddr(detailaddr);
        userOrderDetailDTO.setProd_code(userFastPayDTO.getProd_code());
        userOrderDetailDTO.setProd_name(userFastPayDTO.getProd_name());
        userOrderDetailDTO.setProd_thumbnail(userFastPayDTO.getProd_thumbnail());
        userOrderDetailDTO.setProd_price(userFastPayDTO.getProd_price());
        userOrderDetailDTO.setOrder_qty(userFastPayDTO.getOrder_qty());
        userOrderDetailDTO.setProd_qty(userFastPayDTO.getProd_qty() - userFastPayDTO.getOrder_qty());

        userOrderMapper.insertOrderDetail(userOrderDetailDTO);
        userOrderMapper.updateProd_qty(userOrderDetailDTO);
    }

    @Override
    public List<UserOrderDTO> getUserOrderList(String id) {
        List<UserOrderDTO> orderlist = userOrderMapper.getUserOrderList(id);

        return orderlist;
    }

    @Override
    public List<UserOrderDetailDTO> getUserOrderDetail(int order_no) {
        List<UserOrderDetailDTO> orderDetail = userOrderMapper.getUserOrderDetail(order_no);

        for(UserOrderDetailDTO dto : orderDetail) {
            dto.setTotPrice(dto.getTotPrice());
        }

        return orderDetail;
    }

    @Override
    public void userOrderDelete(int orderNo, List<UserOrderDetailDTO> orderDetail) {


        for(UserOrderDetailDTO dto : orderDetail) {
            if (dto.getOrder_status() == (OrderStatus.ORDERSUCCESS)) {
                userOrderMapper.userOrderDelete(orderNo);
                userOrderMapper.userOrderListDelete(orderNo);
                dto.setProd_qty(dto.getProd_qty() + dto.getOrder_qty());
                int prod_code = dto.getProd_code();
                int prod_qty = dto.getProd_qty();
                userOrderMapper.deleteOrder_qty(prod_code, prod_qty);
            }
        }

    }

    @Override
    public UserOrderCheckDTO getProdInfo(int prodCode) {
        UserOrderCheckDTO getProdInfo = userOrderMapper.getProdInfo(prodCode);
        return getProdInfo;
    }
}
