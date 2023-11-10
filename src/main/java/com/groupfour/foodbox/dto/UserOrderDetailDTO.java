package com.groupfour.foodbox.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UserOrderDetailDTO {

    private int order_detail_no;
    private int order_no;
    private OrderStatus order_status;

    private String user_id;
    private String user_name;
    private String receiver_name;
    private String receiver_tel;
    private String user_zipcode;
    private String user_roadaddr;
    private String user_detailaddr;

    private int prod_code;
    private String prod_name;
    private String prod_thumbnail;
    private int prod_price;
    private int prod_qty;
    private int order_qty;

    private int totPrice;

    private LocalDateTime order_inputDate;

    public void setTotPrice(int totPrice) {
        this.totPrice = prod_price * order_qty;
    }

}
