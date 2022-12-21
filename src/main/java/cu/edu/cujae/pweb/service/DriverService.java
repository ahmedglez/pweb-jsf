package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.bean.UserBean;
import cu.edu.cujae.pweb.dto.DriverDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class DriverService implements CrudInterface {

  @Autowired
  RestService restService;

  @Override
  public List<DriverDto> getAll(){
    List<DriverDto> drivers = new ArrayList<DriverDto>();
    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      ApiRestMapper<DriverDto> apiRestMapper = new ApiRestMapper<>();
      String response = (String)restService.GET("/api/v1/drivers/all", params, String.class,  UserBean.token).getBody();
      drivers = apiRestMapper.mapList(response, DriverDto.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return drivers;
  }

  @Override
  public DriverDto getByCode(int code) {
    DriverDto driver = null;

    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      ApiRestMapper<DriverDto> apiRestMapper = new ApiRestMapper<>();

      UriTemplate template = new UriTemplate("/api/v1/drivers/{code}");
      String uri = template.expand(code).toString();
      String response = (String) restService
        .GET(uri, params, String.class, UserBean.token)
        .getBody();
      driver = apiRestMapper.mapOne(response, DriverDto.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return driver;
  }

  @Override
  public void create(Object dto) {
    DriverDto driver = (DriverDto) dto;
    String response = (String) restService.POST("/api/v1/drivers/", driver, String.class,  UserBean.token).getBody();
    System.out.println(response);
  }

  @Override
  public void update(Object dto) {
    DriverDto driver = (DriverDto) dto;
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    String response = (String) restService.PUT("/api/v1/drivers/", params, driver, String.class,  UserBean.token).getBody();
    System.out.println(response);
  }

  @Override
  public void delete(int code) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    UriTemplate template = new UriTemplate("/api/v1/drivers/{code}");
    String uri = template.expand(code).toString();
    String response = (String) restService
      .DELETE(uri, params, String.class, UserBean.token)
      .getBody();
    System.out.println(response);
  }

  public boolean existID(int code) {
    boolean exist = false;
    List<DriverDto> drivers = getAll();
    for (int i = 0; i < drivers.size(); i++) {
      if (drivers.get(i).getCode() == code) {
        exist = true;
        i = drivers.size();
      }
    }
    return exist;
  }
}
