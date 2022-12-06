package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cu.edu.cujae.pweb.dto.TouristDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.dto.UserDto;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podra inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class UserService implements CrudInterface {
	
	@Autowired 
	private RoleService roleService;
	@Autowired
	private RestService restService;

	@Override
	public  List<UserDto> getAll() {
		List<UserDto> users = new ArrayList<UserDto>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/users/all", params, String.class).getBody();
			users = apiRestMapper.mapList(response, UserDto.class);
			for(UserDto u : users){
				u.rolesName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public UserDto getByCode(int code) {
		UserDto user = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<UserDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/users/{code}");
			String uri = template.expand(code).toString();
			String response = (String)restService.GET(uri, params, String.class).getBody();
			user = apiRestMapper.mapOne(response, UserDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void create(Object dto) {
		UserDto user = (UserDto) dto;
		String response = (String) restService.POST("/users/", user, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void update(Object dto) {
		UserDto user = (UserDto) dto;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/users/", params, user, String.class).getBody();
		System.out.println(response);
	}

	@Override
	public void delete(int code) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/users/{code}");
		String uri = template.expand(code).toString();
		String response = (String) restService.DELETE(uri, params, String.class).getBody();
		System.out.println(response);
	}
}
