package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.MonthlySalesDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.StatisticDTO;
import com.groupfour.foodbox.mapper.admin.StatisticMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService{
    @Autowired
    private StatisticMapper statisticMapper;
    //전체 가입자수
    @Override
    public StatisticDTO numberOfMember() {
        StatisticDTO statisticDTO = statisticMapper.numberOfMember();
        return statisticDTO;
    }
    //탈퇴자 수
    @Override
    public int numberOfWithdrawals() {
        Integer withdrawals = statisticMapper.numberOfWithdrawals();
        if(withdrawals==null){
            withdrawals=0;
        }
        return withdrawals;
    }
    //총매출액
    @Override
    public StatisticDTO totalsales() {
        StatisticDTO statisticDTO = new StatisticDTO();
        int total_sales = statisticMapper.totalsales();
        statisticDTO.setTotal_sales(total_sales);
        List<MonthlySalesDTO> monthlySalesList = statisticMapper.monthlyList();
        statisticDTO.setMonth_sales(monthlySalesList);
        return statisticDTO;
    }
    //기간별 매출액 조회
    @Override
    public int termSales(String start_date, String end_date) {
//        System.out.println("start_date = " + start_date);
//        System.out.println("end_date = " + end_date);
        int total_sales = statisticMapper.termSales(start_date, end_date);

        return total_sales;
    }
    //기간별 매출건수
    @Override
    public int termOrderNum(String start_date, String end_date) {
        int order_num = statisticMapper.termOrderNum(start_date, end_date);
//        System.out.println("order_num = " + order_num);
        return order_num;
    }
    //상품리스트조회
    @Override
    public List<ProductDTO> productList() {
        List<ProductDTO> productDTOList = statisticMapper.productList();
        return productDTOList;
    }
    //상품별 매출액
    @Override
    public int productSales(int prod_code) {
        int total_sales = statisticMapper.productSales(prod_code);
        return total_sales;
    }
    //상품별 매출건수
    @Override
    public int productOrderNum(int prod_code) {
        int order_num = statisticMapper.productOrderNum(prod_code);
        return order_num;
    }
    //상품별 월별 매출
    @Override
    public List<MonthlySalesDTO> mothlSalesList(int prod_code) {
        List<MonthlySalesDTO> monthlySalesList = statisticMapper.mothlSalesList(prod_code);
        return monthlySalesList;
    }
}
