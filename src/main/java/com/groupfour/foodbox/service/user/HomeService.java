package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.mapper.user.HomeMapper;
import com.groupfour.foodbox.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface HomeService {

    List<ProductDTO> productSlider();

}
