package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import cu.edu.cujae.pweb.dto.BrandDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
@Service
public class BrandService implements CrudInterface{
   
    @Autowired
    private RestService restService;

    public List<BrandDto> getBrandByCar(Long brandId) {
        return null;
    }

    public List<BrandDto> getBrandByName(String name) {
        return null;
    }


    @Override
    public  List<BrandDto> getAll() {
        List<BrandDto> brands = new ArrayList<BrandDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/cars/models/brands/all", params, String.class).getBody();
            brands = apiRestMapper.mapList(response, BrandDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return brands;
    }

    @Override
    public BrandDto getById(int code) {
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