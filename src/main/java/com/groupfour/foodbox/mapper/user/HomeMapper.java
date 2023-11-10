package com.groupfour.foodbox.mapper.user;

import com.groupfour.foodbox.domain.ProductDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {
    List<ProductDTO> prodSlideList();


}
