package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.MonthlySalesDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.StatisticDTO;

import java.sql.Date;
import java.util.List;

public interface StatisticService {
    //가입자수 조회
    StatisticDTO numberOfMember();
    //탈퇴자수 조회
    int numberOfWithdrawals();
    //총 매출액
    StatisticDTO totalsales();
    //기간별 매출액 조회
    int termSales(String start_date, String end_date);
    //기간별 매출건수
    int termOrderNum(String start_date, String end_date);
    //상품 리스트
    List<ProductDTO> productList();
    //상품별 매출 조회
    int productSales(int prod_code);
    //상품별 매출 건수
    int productOrderNum(int prod_code);
    //상품별 월별 합계
    List<MonthlySalesDTO> mothlSalesList(int prod_code);
}
