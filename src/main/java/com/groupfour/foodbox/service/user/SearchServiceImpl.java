package com.groupfour.foodbox.service.user;

        import com.groupfour.foodbox.domain.ProductDTO;
        import com.groupfour.foodbox.domain.RecipeDTO;
        import com.groupfour.foodbox.mapper.user.SearchMapper;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    @Override
//    public List<ProductDTO> getProdSearchList(String prod_name) {
    public List<ProductDTO> getProdSearchList(String prod_name) {
//        System.out.println("서비스 prod_name = " + prod_name);
        List<ProductDTO> searchList = searchMapper.getSearchList(prod_name);
        return searchList;
    }

    @Override
//    public List<ProductDTO> getProdSearchList(String prod_name) {
    public List<RecipeDTO> getRecipeinfo(String prod_name) {
//        System.out.println("서비스 prod_name = " + prod_name);
        List<RecipeDTO> searchList = searchMapper.getRecipeinfo(prod_name);

        return searchList;
    }
}

