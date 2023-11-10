package com.groupfour.foodbox.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class ProductDTO {
    private int prod_code;
    private String prod_cat_code;
    private String prod_name;
    private String prod_brand;
    private Date prod_madedate;
    private int prod_price;
    private int prod_qty;
    private String prod_thumbnail;
    private String prod_comment;
    private String prod_country;
    private String prod_spec;
    private Date prod_inputdate;
}
