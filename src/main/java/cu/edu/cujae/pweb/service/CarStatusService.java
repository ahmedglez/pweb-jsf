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
import cu.edu.cujae.pweb.dto.CarStatusDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
@Service
public class CarStatusService implements CrudInterface{
   
    @Autowired
    private RestService restService;

    @Override
    public  List<CarStatusDto> getAll() {
        List<CarStatusDto> statuses = new ArrayList<CarStatusDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarStatusDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/cars/status/all", params, String.class,  UserBean.token).getBody();
            statuses = apiRestMapper.mapList(response, CarStatusDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    @Override
    public CarStatusDto getByCode(int code) {
        CarStatusDto status = null;
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarStatusDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/cars/status/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class,  UserBean.token).getBody();
            status = apiRestMapper.mapOne(response, CarStatusDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public void create(Object dto) {
        CarStatusDto status = (CarStatusDto) dto;
        String response = (String) restService.POST("/api/v1/cars/status/", status, String.class, UserBean.token).getBody();
        System.out.println(response);

    }

    @Override
    public void update(Object dto) {
        CarStatusDto status = (CarStatusDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/api/v1/cars/status/", params, status, String.class, UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/cars/status/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }
}
