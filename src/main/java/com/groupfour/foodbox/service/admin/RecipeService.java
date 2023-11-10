package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.RecipeDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipeService {

    List<RecipeDTO> getList(String RCP_NM,PageDTO pageDTO);

    RecipeDTO recipeInfo(int id);

//    List<List<RecipeDTO>> recipeSearch(String RCP_NM, PageDTO pageDTO);
}
