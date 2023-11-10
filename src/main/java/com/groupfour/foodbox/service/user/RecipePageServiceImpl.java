package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.*;
import com.groupfour.foodbox.mapper.user.RecipePageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class RecipePageServiceImpl implements RecipePageService {
    @Autowired
    private RecipePageMapper recipePageMapper;
    //사용자페이지 레시피 리스트
    @Override
    public List<RecipeDTO> recipeList(ProductPageDTO pageDTO) {
        List<RecipeDTO> recipeList = recipePageMapper.recipeList(pageDTO);
        return recipeList;
    }
    //레시피 총개수
    @Override
    public int recipeCount() {
        int recipeCount = recipePageMapper.recipeCount();
        return recipeCount;
    }
    //레시피 정보가져오기
    @Override
    public RecipeDTO recipeView(Long id) {
        RecipeDTO recipeDTO = recipePageMapper.recipeView(id);
        return recipeDTO;
    }
    //추천상품가져오기
    @Override
    public List<ProductDTO> recommendProduct() {
        List<ProductDTO> productList = recipePageMapper.recommendProduct();
        return productList;
    }
    //사용자가 레시피를 북마크했는지 체크
    @Override
    public BookmarkDTO bookmarkCheck(BookmarkDTO bookmarkDTO) {
        BookmarkDTO dbBookmark = recipePageMapper.bookmarkCheck(bookmarkDTO);
        return dbBookmark;
    }
    //북마크 등록
    @Override
    public int bookmarkRegister(BookmarkDTO bookmarkDTO) {
        int n = recipePageMapper.bookmarkRegister(bookmarkDTO);
        return n;
    }
    //사용자정보가져오기
    @Override
    public UserDTO userCheck(String user_id) {
        UserDTO userDTO = recipePageMapper.userCheck(user_id);
        return userDTO;
    }
    //레시피별 댓글 리스트
    @Override
    public ReplyPageDTO recipeReply(int reply_recipe_code, int viewPage) {
        int recipeReplyCount = recipePageMapper.recipeReplyCount(reply_recipe_code);
        int recipeRatingSum = recipePageMapper.recipeRatingSum(reply_recipe_code);
        if(recipeRatingSum==0) recipeRatingSum=0;
        ReplyPageDTO replyPageDTO = new ReplyPageDTO();
        replyPageDTO.setViewPage(viewPage);
        replyPageDTO.setRecipeRatingSum(recipeRatingSum);
        replyPageDTO.setValue(recipeReplyCount);
        List<RecipeReplyDTO> recipeReplyList = recipePageMapper.recipeReply(reply_recipe_code, replyPageDTO);
        System.out.println("recipeReplyList = " + recipeReplyList);
        replyPageDTO.setRecipeList(recipeReplyList);
        return replyPageDTO;
    }

    @Override
    public int recipeReplyRegister(RecipeReplyDTO recipeReply) {
        int n = recipePageMapper.recipeReplyRegister(recipeReply);
        return n;
    }

    @Override
    public int recipeReplyDelete(int reply_num) {
                int n = recipePageMapper.recipeReplyDelete(reply_num);
        return n;
    }
}

