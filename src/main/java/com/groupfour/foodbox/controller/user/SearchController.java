package com.groupfour.foodbox.controller.user;

import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.RecipeDTO;
import com.groupfour.foodbox.service.user.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    // 검색
    @PostMapping("/user/prodSeach")
    @ResponseBody
    public List<ProductDTO> prodSearch(@RequestParam(value = "prod_name", defaultValue = "noSearch") String prod_name) {
//    public List<ProductDTO> prodSearch(@RequestParam() param) {
//        List<ProductDTO> getProdSearchList = searchService.getProdSearchList(prod_name);
//        System.out.println("param = " + param.get("prod_name")+","+param.get("mode"));
        List<ProductDTO> getProdSearchList = searchService.getProdSearchList(prod_name);

        return getProdSearchList;
    }


    // 레시피 검색
    @PostMapping("/user/recipeSeach")
    @ResponseBody
    public List<RecipeDTO> recipeSearch(@RequestParam(value = "prod_name", defaultValue = "noSearch") String prod_name) {
        List<RecipeDTO> getRecipeSearchList = searchService.getRecipeinfo(prod_name);

        return getRecipeSearchList;
    }

}

