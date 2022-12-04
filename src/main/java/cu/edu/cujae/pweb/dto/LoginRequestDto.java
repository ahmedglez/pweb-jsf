package cu.edu.cujae.pweb.dto;

import org.springframework.lang.NonNull;

public class LoginRequestDto {
	
	@NonNull
    private String username;
	@NonNull
    private String password;

	public LoginRequestDto(String username, String password) {
		super();
		this.password = password;
		this.username = username;
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

}
