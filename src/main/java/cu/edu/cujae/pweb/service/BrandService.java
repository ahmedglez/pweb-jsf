package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.bean.UserBean;
import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class BrandService implements CrudInterface{
   
    @Autowired
    private RestService restService;

    @Override
    public  List<BrandDto> getAll() {
        List<BrandDto> brands = new ArrayList<BrandDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/cars/brands/all", params, String.class,  UserBean.token).getBody();
            brands = apiRestMapper.mapList(response, BrandDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return brands;
    }

    @Override
    public BrandDto getByCode(int code) {
        BrandDto brand = null;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/cars/brands/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class,  UserBean.token).getBody();
            brand = apiRestMapper.mapOne(response, BrandDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brand;
    }

    @Override
    public void create(Object dto) {
        BrandDto brand = (BrandDto) dto;
        String response = (String) restService.POST("/cars/brands/", brand, String.class,  UserBean.token).getBody();
        System.out.println(response);

    }

    @Override
    public void update(Object dto) {
        BrandDto brand = (BrandDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/cars/brands/", params, brand, String.class,  UserBean.token).getBody();
        System.out.println(response);

    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/cars/brands/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }
}