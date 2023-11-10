package com.groupfour.foodbox.mapper.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.ProductImageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    //상품 등록
    void adminProductRegister(Map map);
    //상품상세이미지 등록
    void adminProductImageRegister(String image_prod_image);
    //상품목록
    List<ProductDTO> productList(@Param("keyword") String keyword,
                                 @Param("searchType") String searchType,
                                 @Param("pageDTO")PageDTO pageDTO);
    //상품 개수
    int productCount(@Param("keyword") String keyword,
                     @Param("searchType") String searchType);
    //상품 정보페이지
    ProductDTO productInfo(int prod_code);
    //상품 상세정보 이미지
    List<ProductImageDTO> productImageList(int prod_code);
    //상품 수정
    void adminProductModify(Map map);

    //상품 상세이미지 삭제
    void productImageDelete(Map map);
    //상품 삭제
    void adminProductDelete(int prod_code);
    //상품 상세이미지 삭제
    void adminProductImageDelete(int prod_code);
    //상품 상세이미지 수정
    void adminProductImageModify(String image_prod_image, int prod_code);
}
