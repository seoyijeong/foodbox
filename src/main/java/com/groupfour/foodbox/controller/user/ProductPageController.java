package com.groupfour.foodbox.controller.user;

import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.ProductPageDTO;
import com.groupfour.foodbox.domain.ProductReplyDTO;
import com.groupfour.foodbox.domain.ReplyPageDTO;
import com.groupfour.foodbox.service.user.ProductPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class ProductPageController {
    @Autowired
    private ProductPageService productPageService;

    @GetMapping("/productPage")
    public String productPage(@RequestParam(defaultValue = "ALL") String category_code,
                              @RequestParam(defaultValue = "ALL") String category_name,
                              @RequestParam(defaultValue = "ALL") String prod_spec,
                              @RequestParam(defaultValue = "ALL") String priceSort,
                              ProductPageDTO pageDTO,
                              Model model){
        int productCount = productPageService.productCount(category_code,prod_spec,priceSort);
        pageDTO.setValue(productCount);
        List<ProductDTO> productList = productPageService.productPage(category_code,prod_spec,priceSort,pageDTO);
        model.addAttribute("productList", productList);
        model.addAttribute("category_name",category_name);
        model.addAttribute("prod_spec",prod_spec);
        model.addAttribute("priceSort",priceSort);
        model.addAttribute("category_code",category_code);
        model.addAttribute("pageDTO",pageDTO);
        return "/user/productPage";
    }
    @GetMapping("/productPageRoad/{category_code}/{prod_spec}/{priceSort}/{viewPage}")
    @ResponseBody
//    public List<ProductDTO> productPageRoad(@RequestParam(defaultValue = "ALL") String category_code,
//                              @RequestParam int viewPage,
//                              @RequestParam(defaultValue = "ALL") String prod_spec,
//                              @RequestParam(defaultValue = "ALL") String priceSort,
//                              Model model){
    public List<ProductDTO> productPageRoad(@PathVariable("category_code") String category_code,
                                            @PathVariable("viewPage") int viewPage,
                                            @PathVariable("prod_spec") String prod_spec,
                                            @PathVariable("priceSort") String priceSort){
//        System.out.println("category_code = " + category_code);
        ProductPageDTO pageDTO = new ProductPageDTO();
        int productCount = productPageService.productCount(category_code,prod_spec,priceSort);
        pageDTO.setValue(productCount, viewPage);
        List<ProductDTO> productList = productPageService.productPage(category_code,prod_spec,priceSort,pageDTO);
//        System.out.println("productList = " + productList);
        return productList;
    }
    @GetMapping("/productView")
    public String productView(int prod_code, Model model){

        ProductDTO productDTO = productPageService.productView(prod_code);
        model.addAttribute("productDTO",productDTO);
        List<String> productImageList = productPageService.productImageList(prod_code);
        model.addAttribute("productImageList", productImageList);
        ReplyPageDTO productReplyList = productPageService.productReply(prod_code,1);
        model.addAttribute("productReplyList", productReplyList);
        return "/user/productView";
    }

    @GetMapping("/productReply/{reply_prod_code}/{viewPage}")
    @ResponseBody
    public ReplyPageDTO productReply(@PathVariable("reply_prod_code") int reply_prod_code,
                                     @PathVariable("viewPage") int viewPage){
        ReplyPageDTO replyPageDTO = productPageService.productReply(reply_prod_code, viewPage);
        return replyPageDTO;
    }
    @PostMapping("/prodReplyRegister")
    @ResponseBody
    public String prodReplyRegister(@RequestBody ProductReplyDTO reply){
        int n = productPageService.prodReplyRegister(reply);
        return n==1?"ok":"fail";
    }
    @GetMapping("/prodReplyDelete/{reply_no}/{reply_prod_code}")
    public String prodReplyDelete(@PathVariable("reply_no") int reply_no,
                                  @PathVariable("reply_prod_code") int reply_prod_code){
        int n = productPageService.prodReplyDelete(reply_no);
        return "redirect:/user/productView?prod_code="+reply_prod_code;
    }
}
