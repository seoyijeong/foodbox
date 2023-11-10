package com.groupfour.foodbox.domain;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class StatisticDTO {
    private int num_member;
    private int num_withdrawals;
    private String start_date;
    private String end_date;
    private int order_num;
    private int total_sales;
    private List<MonthlySalesDTO> month_sales;
    private int prod_code;
}
