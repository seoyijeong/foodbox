package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.RecipeDTO;

import java.util.List;

public interface SearchService {

    List<ProductDTO> getProdSearchList(String prod_name);

    List<RecipeDTO> getRecipeinfo(String prod_name);
}




