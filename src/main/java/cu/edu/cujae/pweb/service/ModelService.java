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
import cu.edu.cujae.pweb.dto.CarModelDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class ModelService implements CrudInterface{
    
    @Autowired
    private RestService restService;
    
    @Override
    public  List<CarModelDto> getAll() {
        List<CarModelDto> model = new ArrayList<CarModelDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarModelDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/cars/models/all", params, String.class,  UserBean.token).getBody();
            model = apiRestMapper.mapList(response, CarModelDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public CarModelDto getByCode(int code) {
        CarModelDto model = null;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarModelDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/cars/models/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class,  UserBean.token).getBody();
            model = apiRestMapper.mapOne(response, CarModelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public void create(Object dto) {
        CarModelDto model = (CarModelDto) dto;
        String response = (String) restService.POST("/api/v1/cars/models/", model, String.class, UserBean.token).getBody();
        System.out.println(response);

    }

    @Override
    public void update(Object dto) {
        CarModelDto model = (CarModelDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/api/v1/cars/models/", params, model, String.class, UserBean.token).getBody();
        System.out.println(response);

    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/cars/models/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class, UserBean.token).getBody();
        System.out.println(response);
    }


}
