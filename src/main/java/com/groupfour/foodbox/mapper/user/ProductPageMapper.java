package com.groupfour.foodbox.mapper.user;

import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.ProductPageDTO;
import com.groupfour.foodbox.domain.ProductReplyDTO;
import com.groupfour.foodbox.domain.ReplyPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductPageMapper {
    //사용자페이지 상품리스트 가져오기
    List<ProductDTO> productPage(@Param("category_code") String category_code, @Param("prod_spec") String prod_spec,
                                 @Param("priceSort")String priceSort, @Param("pageDTO") ProductPageDTO pageDTO);
    //사용자페이지 상품상세정보 가져오기
    ProductDTO productView(int prod_code);
    //사용자페이지 상품상세이미지 가져오기
    List<String> productImageList(int prod_code);
    //상품 개수
    int productCount(@Param("category_code") String category_code, @Param("prod_spec") String prod_spec,
                     @Param("priceSort") String priceSort);
    //상품별 댓글 리스트
    List<ProductReplyDTO> productReply(@Param("reply_prod_code")int reply_prod_code, @Param("replyPageDTO") ReplyPageDTO replyPageDTO);
    //상품별 댓글 개수
    int  productReplyCount(int reply_prod_code);
    //상품별 댓글 별점 합계
    Integer productRatingSum(int reply_prod_code);
    //상품 댓글 등록
    int prodReplyRegister(ProductReplyDTO reply);
    //상품 댓글 삭제
    int prodReplyDelete(int reply_no);
}
