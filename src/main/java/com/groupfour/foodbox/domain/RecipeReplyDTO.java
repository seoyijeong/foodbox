package com.groupfour.foodbox.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class RecipeReplyDTO {
    private int reply_num;
    private String reply_recipe_code;
    private String reply_user_id;
    private String reply_content;
    private int reply_rating;
    private Date reply_date;
}
