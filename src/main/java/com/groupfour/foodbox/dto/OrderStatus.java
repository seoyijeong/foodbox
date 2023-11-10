package com.groupfour.foodbox.dto;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ORDERSUCCESS("주문완료"),
    CANCLE("주문취소");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
