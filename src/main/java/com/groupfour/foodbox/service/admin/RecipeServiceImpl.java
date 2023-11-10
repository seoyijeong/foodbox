package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.RecipeDTO;
import com.groupfour.foodbox.mapper.admin.RecipeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeMapper recipeMapper;

    @Override
    public List<RecipeDTO> getList(String RCP_NM,PageDTO pageDTO){

        //DAO영속성 계층
        int totalCnt = recipeMapper.totalCnt(pageDTO);
//        int searchCnt = recipeMapper.searchCnt(RCP_NM);
//        pageDTO.setValue(searchCnt);
        System.out.println("totalCnt = " + totalCnt);
        System.out.println("pageDTO.getCntPerPage() = " + pageDTO.getStartIndex());

        pageDTO.setValue(totalCnt);

        return recipeMapper.getList(RCP_NM,pageDTO);
    }
//    @Override
//    public List<List<RecipeDTO>> recipeSearch(String RCP_NM, PageDTO pageDTO) {
//        int searchCnt = recipeMapper.searchCnt(RCP_NM);
//        pageDTO.setValue(searchCnt);
//        System.out.println("pageDTO.getCntPerPage() = " + pageDTO.getStartIndex());
//
//
//        List<List<RecipeDTO>> list = recipeMapper.recipeSearch(RCP_NM, pageDTO);
//        return list;
//    }



    @Override
    public RecipeDTO recipeInfo(int id) {
        RecipeDTO recipeDTO = recipeMapper.recipeInfo(id);
        return recipeDTO;
    }


}
