package com.groupfour.foodbox.controller.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.ProductImageDTO;
import com.groupfour.foodbox.service.admin.CategoryService;
import com.groupfour.foodbox.service.admin.ProductService;
import com.groupfour.foodbox.util.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;
    //상품등록페이지 이동
    @GetMapping("/productRegister")
    public String productRegister(Model model){
        List<CategoryDTO> categorylist =  categoryService.adminCategoryList();
        model.addAttribute("categorylist", categorylist);
        System.out.println("categorylist = " + categorylist);
        ProductSpec[] prodSpec = ProductSpec.values();
        model.addAttribute("prodSpec", prodSpec);
        return "/admin/product/productRegister";
    }
    //상품 DB에 등록
    @PostMapping("/productRegister")
    public String productRegister(MultipartHttpServletRequest mr, HttpServletRequest request){
        productService.adminProductRegister(mr, request);
        return "redirect:/admin/productList";
    }
    //상품목록페이지로 이동
    @RequestMapping("/productList")
    public String productList(@RequestParam(defaultValue = "ALL") String keyword,
                              @RequestParam(defaultValue="ALL") String searchType,
                              PageDTO pageDTO, Model model){
        int productCount = productService.productCount(keyword,searchType);
        pageDTO.setValue(productCount);
        model.addAttribute("keyword",keyword);
        model.addAttribute("searchType",searchType);
        model.addAttribute("pageDTO", pageDTO);
        List<ProductDTO> productList = productService.productList(keyword,searchType, pageDTO);
        model.addAttribute("productList", productList);
        List<CategoryDTO> categorylist =  categoryService.adminCategoryList();
        model.addAttribute("categorylist", categorylist);
        return "/admin/product/productList";
    }
    //상품 정보페이지로 이동
    @GetMapping("/productInfo")
    public String productInfo(int prod_code, Model model){
        List<CategoryDTO> categorylist =  categoryService.adminCategoryList();
        model.addAttribute("categorylist", categorylist);
        ProductDTO productDTO = productService.productInfo(prod_code);
        model.addAttribute("productDTO", productDTO);
        List<ProductImageDTO> productImageList = productService.productImageList(prod_code);
        model.addAttribute("productImageList", productImageList);
        ProductSpec[] prodSpec = ProductSpec.values();
        model.addAttribute("prodSpec", prodSpec);
        return "/admin/product/productInfo";
    }
    @PostMapping("/productModify")
    public String productModify(MultipartHttpServletRequest mr, HttpServletRequest request){
        productService.adminProductModify(mr, request);
        return "redirect:/admin/productList";
    }
    @GetMapping("/productDelete")
    public String productDelete(int prod_code, HttpServletRequest request){
        productService.adminProductDelete(prod_code, request);
        return "redirect:/admin/productList";
    }
}
