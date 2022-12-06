package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import cu.edu.cujae.pweb.dto.CarModelDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.web.util.UriTemplate;

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
            String response = (String)restService.GET("/cars/models/all", params, String.class).getBody();
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

            UriTemplate template = new UriTemplate("/cars/models/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            model = apiRestMapper.mapOne(response, CarModelDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public void create(Object dto) {
        CarModelDto model = (CarModelDto) dto;
        String response = (String) restService.POST("/cars/models/", model, String.class).getBody();
        System.out.println(response);

    }

    @Override
    public void update(Object dto) {
        CarModelDto model = (CarModelDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/cars/models/", params, model, String.class).getBody();
        System.out.println(response);

    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/cars/models/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }


}
