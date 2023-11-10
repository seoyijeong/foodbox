package com.groupfour.foodbox.service.user;

import com.groupfour.foodbox.domain.UserDTO;
import org.springframework.beans.factory.annotation.Value;

public interface KakaoLoginService {


    public String getAccessToken(String code);

    public UserDTO getUserInfo(String accessToken);



}
