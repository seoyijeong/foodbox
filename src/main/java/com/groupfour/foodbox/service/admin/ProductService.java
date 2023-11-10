package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.ProductImageDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface ProductService {
    //상품등록
    void adminProductRegister(MultipartHttpServletRequest mr, HttpServletRequest request);
    //상품리스트
    List<ProductDTO> productList(String keyword, String searchType, PageDTO pageDTO);
    //상품 개수 조회
    int productCount(String keyword, String searchType);
    //상품 정보페이지
    ProductDTO productInfo(int prod_code);
    //상품상세이미지리스트 조회
    List<ProductImageDTO> productImageList(int prod_code);
    //상품수정
    void adminProductModify(MultipartHttpServletRequest mr, HttpServletRequest request);
    //상품삭제
    void adminProductDelete(int prod_code, HttpServletRequest request);

}
