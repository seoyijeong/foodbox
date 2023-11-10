package com.groupfour.foodbox.controller.user;

import com.groupfour.foodbox.domain.*;
import com.groupfour.foodbox.service.user.RecipePageService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.util.List;

@Controller
@RequestMapping("/user")
public class RecipePageController {
    @Autowired
    private RecipePageService recipePageService;
    //사용자페이지 레시피 리스트
    @GetMapping("/recipe")
    public String recipe(ProductPageDTO pageDTO, Model model){
        int recipeCount = recipePageService.recipeCount();
        pageDTO.setValue(recipeCount);
        model.addAttribute("pageDTO", pageDTO);
        List<RecipeDTO> recipeList = recipePageService.recipeList(pageDTO);
        model.addAttribute("recipeList", recipeList);
        return "/user/recipePage";
    }
    @GetMapping("/recipePageRoad/{viewPage}")
    @ResponseBody
    public List<RecipeDTO> productPageRoad(@PathVariable("viewPage") int viewPage){
        ProductPageDTO pageDTO = new ProductPageDTO();
        int recipeCount = recipePageService.recipeCount();
        pageDTO.setValue(recipeCount,viewPage);
        List<RecipeDTO> recipeList = recipePageService.recipeList(pageDTO);
        return recipeList;
    }
    @GetMapping("/recipeView")
    public String recipeView(Long id, Model model){
        RecipeDTO recipeDTO = recipePageService.recipeView(id);
        model.addAttribute("recipeDTO", recipeDTO);
        List<ProductDTO> productList = recipePageService.recommendProduct();
        model.addAttribute("productList", productList);
        return "/user/recipeView";
    }
    @PostMapping("/bookmarkRegister/{user_id}")
    ///bookmarkRegister/{user_id}") 사용자정보 가져오기

    @ResponseBody
    //responsBody: 자바객체를 제이슨으로 변환
    //RequestBody : json 데이터를 java객체로 변환
    //pathvariable :경로 변수를 표시하기 위해 메서드에 매개변수에 사용된다. /bookmarkRegister/{user_id}") user_id를 받아오기위해
    public String bookmarkRegister(@RequestBody BookmarkDTO bookmarkDTO,
                                   @PathVariable("user_id") String user_id){

        UserDTO userDTO = recipePageService.userCheck(user_id);
        bookmarkDTO.setBm_user_no(userDTO.getUser_no());
        BookmarkDTO dbBookmark = recipePageService.bookmarkCheck(bookmarkDTO);
        if(dbBookmark==null) {
            int n = recipePageService.bookmarkRegister(bookmarkDTO);
            return "register";
        }else if(dbBookmark!=null){
            return "have";
        }
        return "fail";
    }
    //레시피 댓글
    @GetMapping("/recipeReply/{reply_recipe_code}/{viewPage}")
    @ResponseBody
    public ReplyPageDTO replyPageDTO(@PathVariable("reply_recipe_code") int reply_recipe_code,
                                     @PathVariable("viewPage") int viewPage){
        ReplyPageDTO replyPageDTO = recipePageService.recipeReply(reply_recipe_code, viewPage);
        return replyPageDTO;
    }

    //댓글 전송 요청 처리 컨트롤러
    @PostMapping("/recipeReplyRegister")
    @ResponseBody
    public String recipeReplyRegister(@RequestBody RecipeReplyDTO recipeReply){
        int n = recipePageService.recipeReplyRegister(recipeReply);
        //ajax 등록이 잘 되면 ok 리턴
        //ok fail 을 받아서 ajax로 리턴을 받아옴
        //recipeView 300줄
        return n==1?"ok":"fail";
    }
    //댓글 삭제
    @GetMapping("/recipeReplyDelete/{reply_num}/{reply_recipe_code}")
    public String prodReplyDelete(@PathVariable("reply_num") int reply_num,
                                  @PathVariable("reply_recipe_code") int reply_recipe_code){
        int n = recipePageService.recipeReplyDelete(reply_num);
        return "redirect:/user/recipeView?id="+reply_recipe_code;
    }

}
