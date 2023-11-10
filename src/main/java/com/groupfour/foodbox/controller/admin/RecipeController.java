package com.groupfour.foodbox.controller.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.RecipeDTO;
import com.groupfour.foodbox.service.admin.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    //레시피 리스트 페이지 연결
    @RequestMapping("/recipe")
    public String recipeList(@RequestParam(value = "RCP_NM", required = false)String RCP_NM,
            @ModelAttribute("pageDTO") PageDTO pageDTO, Model model){
        //Model 을 넘겨야 view에서 list를 띄울 수 있음.
        /*맵을 사용하여 data 만들기, 리스트를 service로 넘기기*/
        List<RecipeDTO> list = recipeService.getList(RCP_NM,pageDTO);
        System.out.println("RCP_NM = " + RCP_NM);
        model.addAttribute("list",list);
        model.addAttribute("pageDTO", pageDTO);
        //System.out.println("list = " + list);
        pageDTO.setRCP_NM(RCP_NM);
        System.out.println("pageDTO.getRCP_NM() = " + pageDTO.getRCP_NM());
        return "/admin/recipe/recipeList";
    }


    //레시피 수정 클릭시 상세 보기 페이지 이동
    @GetMapping("/recipeInfo/{id}")
    public String recipeInfo(@PathVariable int id, Model model){
        RecipeDTO recipeDTO = recipeService.recipeInfo(id);
        model.addAttribute("recipeDTO", recipeDTO);

        return "/admin/recipe/recipeInfo";
    }
    //메뉴 이름 검색
//    @RequestMapping("/recipe/recipeSearch")
//    public String recipeSearch(@RequestParam(value = "RCP_NM", defaultValue = "ALL")String RCP_NM,
//                               @ModelAttribute("pageDTO") PageDTO pageDTO,
//                                                Model model){
//        List<List<RecipeDTO>> list = recipeService.recipeSearch(RCP_NM, pageDTO);
//        model.addAttribute("list", list);
//        model.addAttribute("pageDTO", pageDTO);
//        System.out.println("pageDTO.getRCP_NM() = " + pageDTO.getRCP_NM());
//
//        return "/admin/recipe/recipeList";
//
//    }




}
