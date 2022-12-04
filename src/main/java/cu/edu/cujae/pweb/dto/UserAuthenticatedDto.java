package cu.edu.cujae.pweb.dto;

import java.util.Collection;
import java.util.List;

public class UserAuthenticatedDto {
	private int code;
	private String username;
	private String password;
	private String email;
	private Collection<RoleDto> roles;
	private String token;
	
	public UserAuthenticatedDto() {
		super();
	}

	public UserAuthenticatedDto(int code, String username, String password, String email,
			Collection<RoleDto> roles, String token) {
		super();
		this.code = code;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.token = token;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setRoles(Collection<RoleDto> roles) {
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<RoleDto> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
