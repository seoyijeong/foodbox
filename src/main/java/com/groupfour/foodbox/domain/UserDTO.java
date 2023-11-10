package com.groupfour.foodbox.domain;


import lombok.Data;

import java.sql.Date;


@Data
public class UserDTO {

        private int user_no;
        private String user_name;
        private String user_id;
        private String user_pw;
        private String user_email;
        private String user_tel;
        private String user_gender;
        private Date user_birthday;
        private String user_zipcode;
        private String user_roadaddr;
        private String user_detailaddr;

        private String new_pw;
}

