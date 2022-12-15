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
import cu.edu.cujae.pweb.dto.BillDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class BillService implements CrudInterface {

    @Autowired
    private RestService restService;

    @Override
    public List<BillDto> getAll() {
        List<BillDto> bills = new ArrayList<BillDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BillDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/contracts/bills/all", params, String.class,  UserBean.token).getBody();
            bills = apiRestMapper.mapList(response, BillDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bills;
    }

    @Override
    public BillDto getByCode(int code) {
        BillDto bill = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BillDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/contracts/bills/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class,  UserBean.token).getBody();
            bill = apiRestMapper.mapOne(response, BillDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bill;
    }

    @Override
    public void create(Object dto) {
        BillDto bill = (BillDto) dto;
        String response = (String) restService.POST("/contracts/bills/", bill, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void update(Object dto) {
        BillDto bill = (BillDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/contracts/bills/", params, bill, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/contracts/bills/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }
}
