package com.groupfour.foodbox.domain;

import lombok.Data;

@Data
public class AdminDTO {
    private String admin_name;
    private String admin_id;
    private String admin_pw;
    private String admin_email;
    private String admin_tel;
}
