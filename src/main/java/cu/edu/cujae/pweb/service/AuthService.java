package cu.edu.cujae.pweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.LoginRequestDto;
import cu.edu.cujae.pweb.dto.UserAuthenticatedDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class AuthService {

    @Autowired
    private RestService restService;

    public UserAuthenticatedDto login(String username, String password) {
        UserAuthenticatedDto authenticatedDto = null;
        try {
            ApiRestMapper<UserAuthenticatedDto> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.POST("/api/v1/auth/login", new LoginRequestDto(username, password), String.class).getBody();
            authenticatedDto = apiRestMapper.mapOne(response, UserAuthenticatedDto.class);
        } catch (Exception e) {
            authenticatedDto = null;
        }
        return authenticatedDto;
    }

}