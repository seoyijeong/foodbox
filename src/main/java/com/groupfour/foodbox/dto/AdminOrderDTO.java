package com.groupfour.foodbox.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AdminOrderDTO {
    private int order_no;
    private OrderStatus order_status;
    private String user_id;
    private String prod_name;
    private String user_name;
    private String receiver_name;
    private String receiver_tel;
    private String user_zipcode;
    private String user_roadaddr;
    private String user_detailaddr;

    private int orderTotPrice;

    private LocalDateTime order_inputDate;
}
