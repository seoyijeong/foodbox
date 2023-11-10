package com.groupfour.foodbox.controller.admin;

import com.groupfour.foodbox.domain.CategoryDTO;
import com.groupfour.foodbox.service.admin.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //카테고리추가 버튼 클릭시 카테고리등록페이지로 넘어감
    @GetMapping("/categoryRegister")
    public String CategoryList(){
        return "/admin/category/categoryRegister";
    }

//    카테고리등록페이지에서 카테고리추가 버튼 클릭시 DB 저장후 카테고리 목록 페이지로 돌아옴
    @PostMapping("/categoryRegister")
    public String CategoryRegister(CategoryDTO categoryDTO){
        categoryService.adminCategoryRegister(categoryDTO);

        return "redirect:/admin/categoryList";
    }
    //카테고리 리스트 불러오기
    @GetMapping("/categoryList")
    public String categoryList(CategoryDTO categoryDTO, Model model){
        List<CategoryDTO> list = categoryService.adminCategoryList();
        //categoryList페이지에 list타입으로 값을 넘겨줌
        model.addAttribute("list", list);
        return "/admin/category/categoryList";
    }
    //카테고리코드 중복체크
    @GetMapping("/checkCategoryCode")
    @ResponseBody
    public String checkCategoryCode(String category_code){
        //db에 관리자가 입력한 카테고리 코드가 있는지 조회
        CategoryDTO dbCategoryDTO = categoryService.checkCategoryCode(category_code);
        if(dbCategoryDTO !=null || "".equals(category_code.trim())){
            return "no";
        }
        return "yes";
    }
    //카테고리코드 클릭시 카테고리수정페이지로 넘어감
    @GetMapping("/categoryInfo")
    public String categoryInfo(@RequestParam("category_no") String category_no, Model model){
        CategoryDTO categoryDTO = categoryService.categoryInfo(category_no);
        //categoryInfo페이지에 categoryDTO타입으로 값을 넘겨줌
        model.addAttribute("categoryDTO", categoryDTO);
        return "/admin/category/categoryInfo";
    }
    //카테고리 수정
    @PostMapping("/categoryModify")
    public String categoryModify(CategoryDTO categoryDTO){
        categoryService.categoryModify(categoryDTO);
        return "redirect:/admin/categoryList";
    }
    //카테고리 삭제
    @GetMapping("/categoryDelete")
    public String categoryDelete(@RequestParam("category_no") String category_no){
        categoryService.categoryDelete(category_no);
        return "redirect:/admin/categoryList";
    }
}
