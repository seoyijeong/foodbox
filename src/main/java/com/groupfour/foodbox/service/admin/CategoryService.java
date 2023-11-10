package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    //카테고리 등록
    void adminCategoryRegister(CategoryDTO categoryDTO);
    //카테고리 리스트
    List<CategoryDTO> adminCategoryList();
    //카테고리 코드 중복조회
    CategoryDTO checkCategoryCode(String categoryCode);
    //카테고리 정보 조회
    CategoryDTO categoryInfo(String category_no);
    //카테고리 수정
    void categoryModify(CategoryDTO categoryDTO);
    //카테고리 삭제
    void categoryDelete(String category_no);
}
