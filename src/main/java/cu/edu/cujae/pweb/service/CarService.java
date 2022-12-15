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
import cu.edu.cujae.pweb.dto.CarDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podra inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class CarService implements CrudInterface {
    
    @Autowired
    private RestService restService;

    @Override
    public  List<CarDto> getAll() {
        List<CarDto> cars = new ArrayList<CarDto>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/cars/all", params, String.class,  UserBean.token).getBody();
            cars = apiRestMapper.mapList(response, CarDto.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public CarDto getByCode(int code) {
        CarDto car = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/cars/{code}");
            String uri = template.expand(code).toString();
            String response = (String)restService.GET(uri, params, String.class,  UserBean.token).getBody();
            car = apiRestMapper.mapOne(response, CarDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public void create(Object dto) {
        CarDto car = (CarDto) dto;
        String response = (String) restService.POST("/cars/", car, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void update(Object dto) {
        CarDto car = (CarDto) dto;
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        String response = (String) restService.PUT("/cars/", params, car, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }

    @Override
    public void delete(int code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/cars/{code}");
        String uri = template.expand(code).toString();
        String response = (String) restService.DELETE(uri, params, String.class,  UserBean.token).getBody();
        System.out.println(response);
    }
    
    public boolean existID(int code){
        boolean exist = false;
        List<CarDto> cars = getAll();
        for(int i = 0; i < cars.size();i++){
            if(cars.get(i).getCode() == code){
                exist = true;
                i = cars.size();
            }
        }
        return exist;
    }
}
