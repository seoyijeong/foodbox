package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import com.groupfour.foodbox.mapper.admin.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;
    //카테고리 등록
    @Override
    public void adminCategoryRegister(CategoryDTO categoryDTO) {
        categoryMapper.adminCategoryRegister(categoryDTO);
    }
    //카테고리 리스트
    @Override
    public List<CategoryDTO> adminCategoryList() {
        List<CategoryDTO> list = categoryMapper.adminCategoryList();
        return list;
    }
    //카테고리 코드 중복조회
    @Override
    public CategoryDTO checkCategoryCode(String category_code) {
        CategoryDTO dbCategoryDTO = categoryMapper.checkCategoryCode(category_code);
        return dbCategoryDTO;
    }
    //카테고리 정보 조회
    @Override
    public CategoryDTO categoryInfo(String category_no) {
        CategoryDTO categoryDTO = categoryMapper.categoryInfo(category_no);
        return categoryDTO;
    }
    //카테고리 정보 수정
    @Override
    public void categoryModify(CategoryDTO categoryDTO) {
        categoryMapper.categoryModify(categoryDTO);
    }
    //카테고리 삭제
    @Override
    public void categoryDelete(String category_no) {
        categoryMapper.categoryDelete(category_no);
    }
}
