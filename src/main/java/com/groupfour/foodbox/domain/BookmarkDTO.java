package com.groupfour.foodbox.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDTO {
    private int bm_no;
    private int bm_user_no;
    private Long bm_recipe_id;
    private String bm_recipe_rcp_nm;
    private String bm_recipe_att_file_no_main;
    private String bm_recipe_rcp_pat2;
    private String bm_recipe_rcp_way2;
}
