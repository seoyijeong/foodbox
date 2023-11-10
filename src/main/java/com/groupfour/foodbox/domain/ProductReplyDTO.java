package com.groupfour.foodbox.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductReplyDTO {
    private int reply_no;
    private int reply_prod_code;
    private String reply_user_id;
    private String reply_content;
    private int reply_rating;
    private Date reply_date;
}
