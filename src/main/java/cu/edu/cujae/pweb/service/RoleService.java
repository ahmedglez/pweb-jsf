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
import cu.edu.cujae.pweb.dto.RoleDto;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.CrudInterface;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class RoleService implements CrudInterface {

	@Autowired
	private RestService restService;

	public List<RoleDto> getRolesByUser(Long userId) {
		return null;
	}


	public List<RoleDto> getRolesByName(String name) {
		return null;
	}


	@Override
	public  List<RoleDto> getAll() {
		List<RoleDto> roles = new ArrayList<RoleDto>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();
			String response = (String)restService.GET("/api/v1/roles/all", params, String.class, UserBean.token).getBody();
			roles = apiRestMapper.mapList(response, RoleDto.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public RoleDto getByCode(int code) {
		RoleDto role = null;
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<RoleDto> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/roles/{code}");
			String uri = template.expand(code).toString();
			String response = (String)restService.GET(uri, params, String.class, UserBean.token).getBody();
			role = apiRestMapper.mapOne(response, RoleDto.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	@Override
	public void create(Object dto) {
		RoleDto user = (RoleDto) dto;
		String response = (String) restService.POST("/api/v1/roles/", user, String.class, UserBean.token).getBody();
		System.out.println(response);

	}

	@Override
	public void update(Object dto) {
		RoleDto user = (RoleDto) dto;
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		String response = (String) restService.PUT("/api/v1/roles/", params, user, String.class, UserBean.token).getBody();
		System.out.println(response);

	}

	@Override
	public void delete(int code) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/roles/{code}");
		String uri = template.expand(code).toString();
		String response = (String) restService.DELETE(uri, params, String.class, UserBean.token).getBody();
		System.out.println(response);
	}
}
