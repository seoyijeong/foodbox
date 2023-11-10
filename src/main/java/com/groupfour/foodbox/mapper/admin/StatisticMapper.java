package com.groupfour.foodbox.mapper.admin;

import com.groupfour.foodbox.domain.MonthlySalesDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.StatisticDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface StatisticMapper {
    //전체 가입자 수
    StatisticDTO numberOfMember();
    //탈퇴자수 조회
    Integer numberOfWithdrawals();
    //총매출
    int totalsales();
    //최근 1년 월별 매출액
    List<MonthlySalesDTO> monthlyList();
    //기간별 매출조회
    int termSales(String start_date, String end_date);
    //기간별 매출 건수 조회
    int termOrderNum(String start_date, String end_date);
    //상품리스트
    List<ProductDTO> productList();
    //상품별 매출조회
    int productSales(int prod_code);
    //상품별 매출건수
    int productOrderNum(int prod_code);
    //상품별 월별 매출
    List<MonthlySalesDTO> mothlSalesList(int prodCode);
}
