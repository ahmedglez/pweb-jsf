package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.AuthenticationRequest;
import cu.edu.cujae.pweb.dto.AuthenticationResponse;
import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.service.AuthService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import cu.edu.cujae.pweb.service.UserService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
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
  public ArrayList<String> roles;
  private boolean admin;
  public static String refreshToken;
  private UserDto userLogged;

  @Autowired
  private AuthService authService;

  @Autowired
  private UserService userService;

  @PostConstruct
  public void init() {
    username = "";
    password = "";
    token = "";
    refreshToken = "";
    roles = new ArrayList<String>();
    admin = false;
  }

  public AuthenticationResponse login() throws IOException {
    try {
      roles = new ArrayList<String>();
      AuthenticationRequest authenticationRequest = new AuthenticationRequest(
        username,
        password
      );
      AuthenticationResponse authenticationResponse = authService.login(
        authenticationRequest
      );
      token = authenticationResponse.getJwttoken();
      refreshToken = authenticationResponse.getRefreshToken();
      java.util.Base64.Decoder decoder = java.util.Base64.getUrlDecoder();
      String[] parts = token.split("\\."); // split out the "parts" (header, payload and signature)

      String headerJson = new String(decoder.decode(parts[0]));
      String payloadJson = new String(decoder.decode(parts[1]));
      String roles_String = payloadJson.substring(
        payloadJson.indexOf("roles") + 8,
        payloadJson.indexOf("]") - 1
      );
      boolean a = roles_String.contains("Administrator");
      admin = a;

      boolean isToken = authenticationResponse.getJwttoken() != null;
      if (isToken) {
        getFacesContext()
          .getExternalContext()
          .redirect(
            getRequest().getContextPath() + "/pages/welcome/welcome.jsf"
          );
        userLogged = userService.getByUsername(username);
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

  protected HttpServletRequest getRequest() {
    return (HttpServletRequest) getFacesContext()
      .getExternalContext()
      .getRequest();
  }

  protected FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
  }

//  public String logout() {
//    return dispatchToUrl("/pages/security/login.xhtml");
//  }

  public String logout(){
    try{
        token = "";
        refreshToken = "";
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
      getFacesContext()
              .getExternalContext()
              .redirect(
                      getRequest().getContextPath() +
                              "/pages/security/login.jsf"
              );
    }catch (Exception e){
      e.printStackTrace();
    }
    return "logout";
  }

 public void updateUsername() throws IOException {
      username = userLogged.getUsername();
   userService.update(userLogged);
   token = "";
   refreshToken = "";
   JsfUtils.addMessageFromBundle(
           null,
           FacesMessage.SEVERITY_INFO,
           "message_user_edited"
   );
     PrimeFaces.current().executeScript("PF('manageUsernameDialog').hide()");
     login();
 }

    public void updateEmail(){
        userService.update(userLogged);
        JsfUtils.addMessageFromBundle(
                null,
                FacesMessage.SEVERITY_INFO,
                "message_user_edited"
        );
        PrimeFaces.current().executeScript("PF('manageEmailDialog').hide()");
    }
    public void updatePassword() throws IOException {
        password = userLogged.getPassword();
        userService.update(userLogged);
        token = "";
        refreshToken = "";
        JsfUtils.addMessageFromBundle(
                null,
                FacesMessage.SEVERITY_INFO,
                "message_user_edited"
        );
        PrimeFaces.current().executeScript("PF('managePasswordDialog').hide()");
        login();
    }

  public UserDto getUserLogged() {
    return userLogged;
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

  public ArrayList<String> getRoles() {
    return roles;
  }

  public void setRoles(ArrayList<String> roles) {
    this.roles = roles;
  }

  public boolean getAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

  public void setUserLogged(UserDto userLogged) {
    this.userLogged = userLogged;
  }


}
