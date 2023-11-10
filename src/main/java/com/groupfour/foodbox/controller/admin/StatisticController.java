package com.groupfour.foodbox.controller.admin;

import com.groupfour.foodbox.domain.MonthlySalesDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.StatisticDTO;
import com.groupfour.foodbox.service.admin.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/statistic")
    public String statistic(Model model, HttpServletRequest request) throws IOException {
        List<ProductDTO> productDTOList = statisticService.productList();
        model.addAttribute("productDTOList", productDTOList);

        return "/admin/statistic/StatisticView";
    }

    @GetMapping("/numberOfMember")
    @ResponseBody
    public StatisticDTO numberOfMember(){
        StatisticDTO statisticDTO = statisticService.numberOfMember();
        int withdrawals = statisticService.numberOfWithdrawals();
        statisticDTO.setNum_withdrawals(withdrawals);
        return statisticDTO;
    }
    @GetMapping("/totalsales")
    @ResponseBody
    public StatisticDTO totalsales(){
        StatisticDTO statisticDTO = statisticService.totalsales();
        return statisticDTO;
    }
    @GetMapping("/termSales/{start_date}/{end_date}")
    @ResponseBody
    public StatisticDTO termSales(@PathVariable("start_date")String start_date,
                                  @PathVariable("end_date")String end_date){
        StatisticDTO statisticDTO = new StatisticDTO();
        int total_sales = statisticService.termSales(start_date, end_date);
        int order_num = statisticService.termOrderNum(start_date, end_date);
        statisticDTO.setTotal_sales(total_sales);
        statisticDTO.setOrder_num(order_num);
        return statisticDTO;
    }
    @GetMapping("/productSales/{prod_code}")
    @ResponseBody
    public StatisticDTO productSales(@PathVariable("prod_code") int prod_code){
        StatisticDTO statisticDTO = new StatisticDTO();
        int total_sales = statisticService.productSales(prod_code);
        int order_num = statisticService.productOrderNum(prod_code);
        List<MonthlySalesDTO> monthlySalesList = statisticService.mothlSalesList(prod_code);
        statisticDTO.setTotal_sales(total_sales);
        statisticDTO.setOrder_num(order_num);
        statisticDTO.setMonth_sales(monthlySalesList);
        return statisticDTO;
    }

}
