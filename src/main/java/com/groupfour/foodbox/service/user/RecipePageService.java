package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecipePageService {
    //사용자페이지 레시피 리스트
    List<RecipeDTO> recipeList(ProductPageDTO pageDTO);
    //레시피 총개수
    int recipeCount();
    //레시피 정보 가져오기
    RecipeDTO recipeView(Long id);
    //사용자가 레시피를 북마크했는지 체크
    BookmarkDTO bookmarkCheck(BookmarkDTO bookmarkDTO);
    //북마크 등록
    int bookmarkRegister(BookmarkDTO bookmarkDTO);
    //사용자 정보가져오기
    UserDTO userCheck(String userId);
    //추천상품 가져오기
    List<ProductDTO> recommendProduct();

    //레시피 댓글
    ReplyPageDTO recipeReply(int reply_recipe_code, int viewPage);

    int recipeReplyRegister(RecipeReplyDTO recipeReply);

    int recipeReplyDelete(int reply_num);
}
