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
import cu.edu.cujae.pweb.dto.TouristDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class TouristServices implements CrudInterface {

    @Autowired
    RestService restService;

    @Override
    public  List<TouristDto> getAll() {
        List<TouristDto> tourists = new ArrayList<TouristDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TouristDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/tourists/all", params, String.class, UserBean.token).getBody();
            tourists = apiRestMapper.mapList(response, TouristDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tourists;
    }

    @Override
    public TouristDto getByCode(int code) {
        TouristDto tourist = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<TouristDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/tourists/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class, UserBean.token).getBody();
            tourist = apiRestMapper.mapOne(response, TouristDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tourist;
    }

    @Override
    public void create(Object dto) {
        TouristDto tourist = (TouristDto) dto;
        String response = (String) restService.POST("/api/v1/tourists/", tourist, String.class, UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void update(Object dto) {
        TouristDto tourist = (TouristDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/api/v1/tourists/", params, tourist, String.class, UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/tourists/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class, UserBean.token).getBody();
        System.out.println(response);
    }

    public boolean existID(int code){
        boolean exist = false;
        List<TouristDto> tourists = getAll();
        for(int i = 0; i < tourists.size();i++){
            if(tourists.get(i).getCode() == code){
                exist = true;
                i = tourists.size();
            }
        }
        return exist;
    }
}
