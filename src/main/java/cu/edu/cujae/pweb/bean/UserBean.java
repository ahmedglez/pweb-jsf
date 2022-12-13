package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.service.AuthService;
import cu.edu.cujae.pweb.service.UserService;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@ViewScoped
public class UserBean {

	private String username;
	private String password;
	private List<UserDto> users;

	@PostConstruct
	public void init() {
		users = users == null ? userService.getAll() : users;
	}

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

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

	public String login(String username, String password) throws IOException {
		boolean isUser = users
				.stream()
				.anyMatch(u ->
						u.getUsername().equals(username) && u.getPassword().equals(password)
				);
		try {
			if (isUser) {
				getFacesContext()
						.getExternalContext()
						.redirect(
								getRequest().getContextPath() + "/pages/welcome/welcome.jsf"
						);
			} else {
				getFacesContext()
						.getExternalContext()
						.redirect(getRequest().getContextPath() + "/pages/login/login.jsf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void forgotPassword(String username) {
		UserDto user = users
				.stream()
				.filter(u -> u.getUsername().equals(username))
				.findFirst()
				.orElse(null);
		authService.sendEmail(user.getEmail());
	}

	protected HttpServletRequest getRequest() {
		return (HttpServletRequest) getFacesContext()
				.getExternalContext()
				.getRequest();
	}

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
}