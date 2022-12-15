package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.AuthenticationRequest;
import cu.edu.cujae.pweb.dto.AuthenticationResponse;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.service.AuthService;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean
@Component
@ViewScoped
@SessionScoped
public class UserBean implements Serializable {

  private String username;
  private String password;
  public static String token;

  @Autowired
  private AuthService authService;

  public UserBean() {}

  public AuthenticationResponse login() throws IOException {
    try {
      AuthenticationRequest authenticationRequest = new AuthenticationRequest(
        username,
        password
      );
      AuthenticationResponse authenticationResponse = authService.login(
        authenticationRequest
      );
      token = authenticationResponse.getToken();
      boolean isToken = authenticationResponse.getJwttoken() != null;
      if (isToken) {
        getFacesContext()
          .getExternalContext()
          .redirect(
            getRequest().getContextPath() + "/pages/welcome/welcome.jsf"
          );
        return authenticationResponse;
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

  /* public void forgotPassword(String username) {
    UserDto user = users
      .stream()
      .filter(u -> u.getUsername().equals(username))
      .findFirst()
      .orElse(null);
    authService.sendEmail(user.getEmail());
  } */

  protected HttpServletRequest getRequest() {
    return (HttpServletRequest) getFacesContext()
      .getExternalContext()
      .getRequest();
  }

  protected FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
  }

  public String logout() {
    return dispatchToUrl("/logout");
  }

  public String getUserLogued() {
    return CurrentUserUtils.getUsername();
  }

  private String dispatchToUrl(String url) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) facesContext
      .getExternalContext()
      .getRequest();
    HttpServletResponse response = (HttpServletResponse) facesContext
      .getExternalContext()
      .getResponse();
    RequestDispatcher dispatcher = request.getRequestDispatcher(url);
    try {
      dispatcher.forward(request, response);
      facesContext.responseComplete();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
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

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public AuthService getAuthService() {
    return authService;
  }

  public void setAuthService(AuthService authService) {
    this.authService = authService;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
