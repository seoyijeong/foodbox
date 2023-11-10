package com.groupfour.foodbox.controller.user;

import com.groupfour.foodbox.dto.UserCartDTO;
import com.groupfour.foodbox.service.user.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserCartController {

    @Autowired
    UserCartService userCartService;

    @PostMapping("/addCart")
    public @ResponseBody void insertCart(@RequestBody UserCartDTO userCartDTO) {

        String id = userCartDTO.getUser_id();

        UserCartDTO userCartDtoId = userCartService.checkCart(id, userCartDTO.getProd_code());

        if(userCartDtoId == null) {
            userCartService.insertCart(userCartDTO);
        } else {
            int order_qty = userCartDTO.getOrder_qty() + userCartDtoId.getOrder_qty();
            userCartService.modifyQty(order_qty, userCartDtoId.getCart_no());
        }

    }

    @GetMapping("/userCartList/{user_id}")
    public String userCartList(UserCartDTO userCartDTO, Model model) {
        List<UserCartDTO> cartList = userCartService.userCartList(userCartDTO.getUser_id());
        int cartTotPrice = 0;

        for(UserCartDTO userDto: cartList) {
            userDto.setTotPrice(userDto.getTotPrice());
            cartTotPrice += userDto.getTotPrice();
        }

        model.addAttribute("cartList", cartList);
        model.addAttribute("cartTotPrice", cartTotPrice);

        return "/user/userCartList";
    }

    @PostMapping("/userCartModify")
    public String userCartModify(UserCartDTO userCartDTO) {
        String user_id = userCartDTO.getUser_id();

        userCartService.modifyQty(userCartDTO.getOrder_qty(), userCartDTO.getCart_no());

        return "redirect:/user/userCartList/" + user_id;
    }

    @PostMapping("/userCartDelete")
    public @ResponseBody void userCartDelete(@RequestBody UserCartDTO userCartDTO) {
        int cart_no = userCartDTO.getCart_no();

        userCartService.userCartDelete(cart_no);
    }
}
