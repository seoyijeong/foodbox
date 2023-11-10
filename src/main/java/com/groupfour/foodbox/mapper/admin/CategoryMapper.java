package com.groupfour.foodbox.mapper.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //카테고리등록
    void adminCategoryRegister(CategoryDTO categoryDTO);
    //카테고리 리스트
    List<CategoryDTO> adminCategoryList();
    //카테고리 코드 중복체크
    CategoryDTO checkCategoryCode(String categoryCode);
    //카테고리 정보조회
    CategoryDTO categoryInfo(String category_no);
    //카테고리 정보수정
    void categoryModify(CategoryDTO categoryDTO);
    //카테고리 삭제
    void categoryDelete(String category_no);
}
