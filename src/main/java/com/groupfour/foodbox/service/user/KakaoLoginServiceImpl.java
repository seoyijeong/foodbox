package com.groupfour.foodbox.service.user;

import com.google.gson.Gson;
import com.groupfour.foodbox.domain.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Map;


@Service
public class KakaoLoginServiceImpl implements KakaoLoginService{

    @Value("${kakao.default.password}")
    String kakaoPassword; // kakaoPassword변수로 받아옴

    //액세스 토큰 획득하기
    public String getAccessToken(String code){
        HttpHeaders header =new HttpHeaders();
        header.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        //다중 값(multi-value)을 저장하고 관리하기 위한 자료구조, 하나의 키(key)에 여러 개의 값(value)을 연결하고자 할 때 사용
        LinkedMultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("grant_type","authorization_code");
        body.add("client_id","ea90d7e3863b99ec87024ee49f5015f3");
        body.add("redirect_uri","http://localhost:8800/oauth/kakao");
        body.add("code",code);

        // RequestBody와 기능이 비슷하지만 헤더 정보를 포함하는 객체
        //RequestEntity는 일반적으로 RestTemplate이나 WebClient와 같은 스프링의 HTTP 클라이언트를 사용할 때 요청을 구성하는데 활용
        HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, header);

        // 웹브라우저 역할을 하는 객체
        RestTemplate restTemplate = new RestTemplate();

        // 특정 서버에 요청을 전달하기
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token", // token 요청 주소
                HttpMethod.POST, // 요청방식
                requestEntity, // http 요청 메세지
                String.class    // 응답 받을 타입
        );

        String userInfo = responseEntity.getBody();

        // GSON 라이브러리를 이용한 액세스 토큰 값 추출하기
        Gson gson = new Gson();
        Map<?,?> mapData = gson.fromJson(userInfo, Map.class); // Map 컬레션 구조로 변경

        //return responseEntity.getBody(); // header와 body 정보 중 body만 가져오기
        System.out.println(requestEntity.getBody());

        // Map구조에서 value만 리턴
        return (String) mapData.get("access_token");

    }

    @Override
    public UserDTO getUserInfo(String accessToken) {
        // ############# http 요청 메세지 생성 #############
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Bearer " + accessToken);
        header.add("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        // body 생략가능
        HttpEntity<LinkedMultiValueMap<String, String>> requestEntity = new HttpEntity<>(header);
        // ############# http 요청 메세지 생성 끝 #############

        // 웹브라우저 역할을 하는 객체
        RestTemplate restTemplate = new RestTemplate();

        // 특정 서버에 요청을 전달하기
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me", // 사용자 정보 요청 주소
                HttpMethod.POST, // 요청방식
                requestEntity, // http 요청 메세지
                String.class    // 응답 받을 타입
        );

        String userInfo = responseEntity.getBody();

        Gson gson = new Gson();
        Map<?,?> mapData= gson.fromJson(userInfo, Map.class);

//        String nickname= (String) ((Map<?,?>)(mapData.get("kakao_account"))).get("nickname");
        String email= (String) ((Map<?,?>)(mapData.get("kakao_account"))).get("email");

        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(email);
        userDTO.setUser_pw(kakaoPassword);
        userDTO.setUser_email(email);
        userDTO.setUser_name("?");
        userDTO.setUser_tel("010");
        userDTO.setUser_gender("?");
        userDTO.setUser_birthday(Date.valueOf("2020-08-12"));
        userDTO.setUser_detailaddr("?");


//        return (String) responseEntity.getBody();
        return userDTO;
    }

}
