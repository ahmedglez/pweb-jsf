package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.CarStatusDto;
import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
@Service
public class CarStatusService implements CrudInterface{
   
    @Autowired
    private RestService restService;

    public List<CarStatusDto> getStatusByCar(Long carId) {
        return null;
    }

    public List<CarStatusDto> getStatusByName(String name) {
        return null;
    }


    @Override
    public  List<CarStatusDto> getAll() {
        List<CarStatusDto> statuses = new ArrayList<CarStatusDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarStatusDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/cars/status/all", params, String.class).getBody();
            statuses = apiRestMapper.mapList(response, CarStatusDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    @Override
    public CarStatusDto getById(int code) {
//        CarStatusDto status = null;
//        try {
//            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//            ApiRestMapper<CarStatusDto> apiRestMapper = new ApiRestMapper<>();
//
//            UriTemplate template = new UriTemplate("/status/{code}");
//            String uri = template.expand(code).toString();
//            String response = (String)restService.GET(uri, params, String.class).getBody();
//            status = apiRestMapper.mapOne(response, CarStatusDto.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return status;
        return null;
    }

    @Override
    public void create(Object dto) {
//        CarStatusDto user = (CarStatusDto) dto;
//        String response = (String) restService.POST("/roles/", user, String.class).getBody();
//        System.out.println(response);

    }

    @Override
    public void update(Object dto) {
//        RoleDto user = (RoleDto) dto;
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        String response = (String) restService.PUT("/roles/", params, user, String.class).getBody();
//        System.out.println(response);

    }

    @Override
    public void delete(int code) {
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        UriTemplate template = new UriTemplate("/roles/{code}");
//        String uri = template.expand(code).toString();
//        String response = (String) restService.DELETE(uri, params, String.class).getBody();
//        System.out.println(response);
    }
}
