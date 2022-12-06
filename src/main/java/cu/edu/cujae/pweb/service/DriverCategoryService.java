package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.DriversCategoriesDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DriverCategoryService implements CrudInterface {

    @Autowired
    RestService restService;

    @Override
    public List<DriversCategoriesDto> getAll() {
        List<DriversCategoriesDto> categories = new ArrayList<DriversCategoriesDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriversCategoriesDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/drivers/categories/all", params, String.class).getBody();
            categories = apiRestMapper.mapList(response, DriversCategoriesDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public DriversCategoriesDto getByCode(int code){
        DriversCategoriesDto category = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriversCategoriesDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/drivers/categories/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            category = apiRestMapper.mapOne(response, DriversCategoriesDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;

    }

    @Override
    public void create(Object dto) {
        DriversCategoriesDto category = (DriversCategoriesDto) dto;
        String response = (String) restService.POST("/drivers/categories/", category, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void update(Object dto) {
        DriversCategoriesDto category = (DriversCategoriesDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/drivers/categories/", params, category, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/drivers/categories/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }
}
