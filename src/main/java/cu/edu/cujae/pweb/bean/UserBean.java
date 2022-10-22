package cu.edu.cujae.pweb.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class UserBean {
	
	private String username;
	private String password;
	
	public UserBean() {
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
	
	public String login() {
		if(username.equalsIgnoreCase("panitas") && password.equals("1234")) {
			try {
				getFacesContext().getExternalContext().redirect(getRequest().getContextPath() +
					    "/pages/welcome/welcome.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return  null;
	}
	
	protected HttpServletRequest getRequest() {
	    return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}
	
	protected FacesContext getFacesContext() {
	    return FacesContext.getCurrentInstance();
	}
}
