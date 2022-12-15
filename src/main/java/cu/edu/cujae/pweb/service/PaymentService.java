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
import cu.edu.cujae.pweb.dto.PaymentsDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class PaymentService implements CrudInterface {

    @Autowired
    private RestService restService;

    @Override
    public List<PaymentsDto> getAll() {
        List<PaymentsDto> payments = new ArrayList<PaymentsDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<PaymentsDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/contracts/payments/all", params, String.class, UserBean.token).getBody();
            payments = apiRestMapper.mapList(response, PaymentsDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payments;
    }

    @Override
    public PaymentsDto getByCode(int code) {
        PaymentsDto payment = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<PaymentsDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/contracts/payments/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class, UserBean.token).getBody();
            payment = apiRestMapper.mapOne(response, PaymentsDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payment;
    }

    @Override
    public void create(Object dto) {
        PaymentsDto payment = (PaymentsDto) dto;
        String response = (String) restService.POST("/contracts/payments/", payment, String.class, UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void update(Object dto) {
        PaymentsDto payment = (PaymentsDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/contracts/payments/", params, payment, String.class, UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/contracts/payments/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class, UserBean.token).getBody();
        System.out.println(response);
    }
}
