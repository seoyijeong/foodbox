package com.groupfour.foodbox.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    private Long id;
    private String RCP_SEQ; // Recipe Sequence Number
    private String RCP_NM; // Menu Name
    private String RCP_WAY2; // Cooking Method
    private String RCP_PAT2; // Dish Type
    private String INFO_WGT; // Weight (per serving)
    private String INFO_ENG; // Calories
    private String INFO_CAR; // Carbohydrates
    private String INFO_PRO; // Protein
    private String INFO_FAT; // Fat
    private String INFO_NA; // Sodium
    private String HASH_TAG; // Hash Tag
    private String ATT_FILE_NO_MAIN; // Image Path (Small)
    private String ATT_FILE_NO_MK; // Image Path (Large)
    private String RCP_PARTS_DTLS; // Ingredient Information
    private String MANUAL01; // Cooking Method 01
    private String MANUAL_IMG01; // Cooking Method Image 01
    private String MANUAL02; // Cooking Method 02
    private String MANUAL_IMG02; // Cooking Method Image 02
    private String MANUAL03; // Cooking Method 03
    private String MANUAL_IMG03; // Cooking Method Image 03
    private String MANUAL04; // Cooking Method 04
    private String MANUAL_IMG04; // Cooking Method Image 04
    private String MANUAL05; // Cooking Method 05
    private String MANUAL_IMG05; // Cooking Method Image 05
    private String MANUAL06; // Cooking Method 06
    private String MANUAL_IMG06; // Cooking Method Image 06
    private String MANUAL07; // Cooking Method 07
    private String MANUAL_IMG07; // Cooking Method Image 07
    private String MANUAL08; // Cooking Method 08
    private String MANUAL_IMG08; // Cooking Method Image 08
    private String MANUAL09; // Cooking Method 09
    private String MANUAL_IMG09; // Cooking Method Image 09
    private String MANUAL10; // Cooking Method 10
    private String MANUAL_IMG10; // Cooking Method Image 10
    private String MANUAL11; // Cooking Method 11
    private String MANUAL_IMG11; // Cooking Method Image 11
    private String MANUAL12; // Cooking Method 12
    private String MANUAL_IMG12; // Cooking Method Image 12
    private String MANUAL13; // Cooking Method 13
    private String MANUAL_IMG13; // Cooking Method Image 13
    private String MANUAL14; // Cooking Method 14
    private String MANUAL_IMG14; // Cooking Method Image 14
    private String MANUAL15; // Cooking Method 15
    private String MANUAL_IMG15; // Cooking Method Image 15
    private String MANUAL16; // Cooking Method 16
    private String MANUAL_IMG16; // Cooking Method Image 16
    private String MANUAL17; // Cooking Method 17
    private String MANUAL_IMG17; // Cooking Method Image 17
    private String MANUAL18; // Cooking Method 18
    private String MANUAL_IMG18; // Cooking Method Image 18
    private String MANUAL19; // Cooking Method 19
    private String MANUAL_IMG19; // Cooking Method Image 19
    private String MANUAL20; // Cooking Method 20
    private String MANUAL_IMG20; // Cooking Method Image 20
    private String RCP_NA_TIP; // Reduction Cooking Tip
}
