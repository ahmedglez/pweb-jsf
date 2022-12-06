package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.ContractDto;
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
public class ContractService implements CrudInterface {

    @Autowired
    private RestService restService;

    @Override
    public  List<ContractDto> getAll() {
        List<ContractDto> contracts = new ArrayList<ContractDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/contracts/all", params, String.class).getBody();
            contracts = apiRestMapper.mapList(response, ContractDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    @Override
    public ContractDto getByCode(int code) {
        ContractDto contract = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/contracts/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            contract = apiRestMapper.mapOne(response, ContractDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contract;
    }

    @Override
    public void create(Object dto) {
        ContractDto contract = (ContractDto) dto;
        String response = (String) restService.POST("/contracts/", contract, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void update(Object dto) {
        ContractDto contract = (ContractDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/contracts/", params, contract, String.class).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/contracts/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class).getBody();
        System.out.println(response);
    }
}
