package com.groupfour.foodbox.mapper.user;

import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.RecipeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<ProductDTO> getSearchList(String prod_name);

    List<RecipeDTO> getRecipeinfo(String prod_name);
}

