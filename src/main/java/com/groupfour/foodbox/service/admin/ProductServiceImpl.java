package com.groupfour.foodbox.service.admin;

import com.groupfour.foodbox.domain.PageDTO;
import com.groupfour.foodbox.domain.ProductDTO;
import com.groupfour.foodbox.domain.ProductImageDTO;
import com.groupfour.foodbox.mapper.admin.ProductMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductMapper productMapper;

    //상품 등록
    @SneakyThrows
    @Override
    public void adminProductRegister(MultipartHttpServletRequest mr, HttpServletRequest request) {
        // 이미지 저장 경로
        String savePath = "C:\\foodbox\\foodbox\\src\\main\\resources\\static\\image";
        System.out.println(savePath);

        Map map = new HashMap();

        //mhr 텍스트 파일, 바이너리 파일의 정보를 모두 얻어올 수 있음
        Enumeration<String> enu = mr.getParameterNames();
        //일반 텍스트파일의 파라미터명(key), 해당 파라미터명의 value값을 가져옴
        while(enu.hasMoreElements()) {
            String paramName = enu.nextElement();
            String paramValue = mr.getParameter(paramName);
//            System.out.println(paramName + " : "+ paramValue);
            map.put(paramName, paramValue);
        }
        //파일
        Iterator<String> iter = mr.getFileNames();

        List<String> fileList = new ArrayList<String>();

        while(iter.hasNext()) {

            String fileParamName = iter.next();
            System.out.println("fileParamName : "+ fileParamName);

            //MultipartFile : 파일정보를 갖고 있는 객체
            MultipartFile mFile = mr.getFile(fileParamName);
            String originName = mFile.getOriginalFilename();
//            System.out.println("originName : " + originName);

            File file = new File(savePath+"\\"+fileParamName);
            if(mFile.getSize() != 0) {//업로드된 경우
                if(!file.exists()) {//파일이 존재하지 않으면
                    if(file.getParentFile().mkdir()) {
                        file.createNewFile();//임시파일 생성

                    }//if
                }//if
                File uploadFile = new File(savePath+"\\"+originName);
                //중복시 파일명 대체
                if(uploadFile.exists()) {
                    originName = System.currentTimeMillis()+" "+originName;
                    uploadFile = new File(savePath+"\\"+originName);
                }

                //실제 파일을 업로드하기
                mFile.transferTo(uploadFile);
                fileList.add(originName);
                map.put(fileParamName, originName);
            }//if
        }//while
        System.out.println("map = " + map);
        for(int i=0; i<30; i++) {
            productMapper.adminProductRegister(map);
            for (Object objkey : map.keySet()) {
                String key = objkey.toString();
//                System.out.println(String.format("키 -> %s, 값 -> %s", key, map.get(key)));
                String keyValue = map.get(key).toString();
                if (key.contains("image_prod_image")) {
                    productMapper.adminProductImageRegister(keyValue);
                }
            }
        }
    }
    //상품리스트
    @Override
    public List<ProductDTO> productList(String keyword, String searchType, PageDTO pageDTO) {

        List<ProductDTO> productList = productMapper.productList(keyword,searchType, pageDTO);
        return productList;
    }

    @Override
    public int productCount(String keyword, String searchType) {
        int productCount =productMapper.productCount(keyword,searchType);
        return productCount;
    }

    //상품상세정보
    @Override
    public ProductDTO productInfo(int prod_code) {
        ProductDTO productDTO = productMapper.productInfo(prod_code);
        return productDTO;
    }
    //상품 상세이미지리스트
    @Override
    public List<ProductImageDTO> productImageList(int prod_code) {
        List<ProductImageDTO> productImageList = productMapper.productImageList(prod_code);
        return productImageList;
    }
    //상품 수정
    @SneakyThrows
    @Override
    public void adminProductModify(MultipartHttpServletRequest mr, HttpServletRequest request) {
        // 이미지 저장 경로
        String savePath = "C:\\foodbox\\foodbox\\src\\main\\resources\\static\\image";
        System.out.println(savePath);

        Map map = new HashMap();

        //mhr 텍스트 파일, 바이너리 파일의 정보를 모두 얻어올 수 있음
        Enumeration<String> enu = mr.getParameterNames();
        //일반 텍스트파일의 파라미터명(key), 해당 파라미터명의 value값을 가져옴
        while (enu.hasMoreElements()) {
            String paramName = enu.nextElement();
            String paramValue = mr.getParameter(paramName);
//            System.out.println(paramName + " : " + paramValue);
            map.put(paramName, paramValue);
        }
        int oldimageCnt = 0;

        for(int i = 0; i<map.size(); i++) {

                if(map.get("image_prod_image"+i+"old")!= null){
                oldimageCnt++;
            }
        }
        if(map.get("clickRemove").equals("click")){
            File delFIle = null;
            for(int i=1; i<oldimageCnt+1;i++) {
                delFIle = new File(savePath + "/" + map.get("image_prod_image" + i + "old"));
                if (delFIle.exists()) {
                    delFIle.delete();
                }
            }
        }
        //파일
        Iterator<String> iter = mr.getFileNames();

        List<String> fileList = new ArrayList<String>();

        while (iter.hasNext()) {

            String fileParamName = iter.next();
//            System.out.println("fileParamName : " + fileParamName);
            //MultipartFile : 파일정보를 갖고 있는 객체
            MultipartFile mFile = mr.getFile(fileParamName);
            String originName = mFile.getOriginalFilename();

            if((!originName.equals(""))&&fileParamName.contains("image_prod_image")){
                File delFIle = null;
                delFIle = new File(savePath + "/" + map.get(fileParamName + "old"));
                if (delFIle.exists()) {
                    delFIle.delete();
                }
            }
            if((!originName.equals(""))&&fileParamName.contains("prod_thumbnail")){
                File uploadFile = new File(savePath+"\\"+originName);
                //중복시 파일명 대체
                if(uploadFile.exists()) {
                    originName = System.currentTimeMillis()+" "+originName;
                    uploadFile = new File(savePath+"\\"+originName);
                }
            }

            if(originName.equals("")&& fileParamName.equals("prod_thumbnail")){
                originName = mr.getParameter("prod_thumbnailold");
                map.put(fileParamName, originName);
            }
            if(originName.equals("")&& fileParamName.contains("image_prod_image")){
                originName = mr.getParameter(fileParamName+"old");
                map.put(fileParamName, originName);
            }


            System.out.println("originName : " + originName);

            File file = new File(savePath + "\\" + fileParamName);
            if (mFile.getSize() != 0) {//업로드된 경우
                if (!file.exists()) {//파일이 존재하지 않으면
                    if (file.getParentFile().mkdir()) {
                        file.createNewFile();//임시파일 생성

                    }//if
                }//if
                File uploadFile = new File(savePath + "\\" + originName);
                //실제 파일을 업로드하기
                mFile.transferTo(uploadFile);
                fileList.add(originName);

                map.put(fileParamName, originName);
            }//if
        }//while
        System.out.println("map = " + map);

        productMapper.productImageDelete(map);

        productMapper.adminProductModify(map);
        for (Object objkey : map.keySet()) {
            String key = objkey.toString();
//            System.out.println(String.format("키 -> %s, 값 -> %s", key, map.get(key)));
            String keyValue = map.get(key).toString();
            if (key.contains("image_prod_image") && map.get(key) != null && !key.contains("old")) {
                productMapper.adminProductImageModify(keyValue,Integer.parseInt((String)map.get("prod_code")));
            }
        }
    }



    @Override
    public void adminProductDelete(int prod_code, HttpServletRequest request) {
        String realPath = "C:\\foodbox\\foodbox\\src\\main\\resources\\static\\image";
        List<ProductImageDTO> productImageList = productMapper.productImageList(prod_code);
        File delFIle = null;
        for(ProductImageDTO productImageDTO : productImageList){
            delFIle = new File(realPath + "/" + productImageDTO.getImage_prod_image());
            if(delFIle.exists()){
                if(delFIle.delete()) System.out.println("상세이미지 삭제");
            }
        }
        productMapper.adminProductDelete(prod_code);
        productMapper.adminProductImageDelete(prod_code);
    }

}
