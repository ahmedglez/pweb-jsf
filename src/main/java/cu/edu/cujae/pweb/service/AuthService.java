package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.bean.UserBean;
import cu.edu.cujae.pweb.dto.AuthenticationRequest;
import cu.edu.cujae.pweb.dto.AuthenticationResponse;
import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.dto.UserForRecoverCode;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

@Service
public class AuthService {

  @Autowired
  private RestService restService;

  public AuthenticationResponse login(
    AuthenticationRequest authenticationRequest
  ) {
    AuthenticationResponse authenticationResponse = null;
    try {
      ApiRestMapper<AuthenticationResponse> apiRestMapper = new ApiRestMapper<>();
      UriTemplate template = new UriTemplate("/api/v1/login");
      String uri = template.expand().toString();
      System.out.println("uri: " + uri);
      String response = (String) restService
        .POST(uri, authenticationRequest, String.class, UserBean.token)
        .getBody();
      authenticationResponse =
        apiRestMapper.mapOne(response, AuthenticationResponse.class);
      System.out.println("token: " + authenticationResponse.getToken());
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        authenticationRequest.getUsername(),
        authenticationRequest.getPassword()
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return authenticationResponse;
  }

  public UserDto logout(Object dto) {
    UserDto user = (UserDto) dto;
    try {
      ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
      UriTemplate template = new UriTemplate("/api/v1/logout");
      String uri = template.expand().toString();
      String response = (String) restService
        .POST(uri, user, String.class, UserBean.token)
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

      UriTemplate template = new UriTemplate("/api/v1/sendmail/{email}");
      String uri = template.expand(email).toString();
      String response = (String) restService
        .GET(uri, params, String.class)
        .getBody();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public UserDto sendRecoveryCode(UserForRecoverCode userForRecoverCode) {
    UserDto userDto = null;
    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

      UriTemplate template = new UriTemplate("/api/v1/checkRecoveryCode");
      String uri = template.expand().toString();
      String response = (String) restService
        .POST(uri, userForRecoverCode, String.class)
        .getBody();
      userDto = apiRestMapper.mapOne(response, UserDto.class);
      return userDto;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userDto;
  }

  public String recoverPassword(UserDto userDto) {
    String response = null;
    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

      UriTemplate template = new UriTemplate("/api/v1/changePassword");
      String uri = template.expand().toString();
      response =
        (String) restService.POST(uri, userDto, String.class).getBody();
      return response;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }


}
