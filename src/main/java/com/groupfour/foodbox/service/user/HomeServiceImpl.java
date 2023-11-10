package com.groupfour.foodbox.service.user;


import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.mapper.user.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService{

    @Autowired
    private HomeMapper homeMapper;


    @Override
    public List<ProductDTO> productSlider() {
        List<ProductDTO> prodSlideList = homeMapper.prodSlideList();
        return prodSlideList;
    }

}
