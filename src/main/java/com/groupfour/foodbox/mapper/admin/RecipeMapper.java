package com.groupfour.foodbox.mapper.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.RecipeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface RecipeMapper {
     List<RecipeDTO> getList(@Param("RCP_NM")String RCP_NM,@Param("pageDTO")PageDTO pageDTO);


    int totalCnt(PageDTO pageDTO);
    int searchCnt(String RCP_NM);

    RecipeDTO recipeInfo(int id);

    //List<List<RecipeDTO>> recipeSearch(@Param("RCP_NM") String RCP_NM, @Param("pageDTO") PageDTO pageDTO);


}
