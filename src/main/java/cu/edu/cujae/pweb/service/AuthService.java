package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class AuthService {

    @Autowired
    private RestService restService;

    public UserDto login(Object dto) {
        UserDto user = (UserDto) dto;
        try {
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
            UriTemplate template = new UriTemplate("/login");
            String uri = template.expand().toString();
            String response = (String) restService
                    .POST(uri, user, String.class)
                    .getBody();
            user = apiRestMapper.mapOne(response, UserDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public UserDto logout(Object dto) {
        UserDto user = (UserDto) dto;
        try {
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
            UriTemplate template = new UriTemplate("/logout");
            String uri = template.expand().toString();
            String response = (String) restService
                    .POST(uri, user, String.class)
                    .getBody();
            user = apiRestMapper.mapOne(response, UserDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void sendEmail(String email) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/sendmail/{email}");
            String uri = template.expand(email).toString();
            String response = (String) restService
                    .GET(uri, params, String.class)
                    .getBody();
            apiRestMapper.mapOne(response, UserDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
