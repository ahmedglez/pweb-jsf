package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.UserDto;
import cu.edu.cujae.pweb.dto.UserForRecoverCode;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.service.AuthService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import cu.edu.cujae.pweb.utils.PasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class RecoverPasswordBean {

  private String email;

  private String recoveryCode;

  private String password;

  private String confirmPassword;

  private UserDto user;

  private PasswordEncoderUtils passwordEncoderUtils = new PasswordEncoderUtils();

  @Autowired
  private AuthService authService;

  @PostConstruct
  public void init() {}

  public void sendRecoveryEmail() {
    try {
      authService.sendEmail(email);
      getFacesContext()
        .getExternalContext()
        .redirect(
          getRequest().getContextPath() +
          "/pages/security/recover_password/recovery-code.jsf"
        );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void sendRecoveryCode() {
    try {
      UserForRecoverCode userForRecoverCode = new UserForRecoverCode();
      userForRecoverCode.setEmail(email);
      userForRecoverCode.setRecoveryCode(recoveryCode);
      user = authService.sendRecoveryCode(userForRecoverCode);
      getFacesContext()
        .getExternalContext()
        .redirect(
          getRequest().getContextPath() +
          "/pages/security/recover_password/new-password.jsf"
        );
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void changePassword() {
    try {
      user.setPassword(passwordEncoderUtils.encode(password));
      authService.recoverPassword(user);
      getFacesContext()
        .getExternalContext()
        .redirect(getRequest().getContextPath() + "/pages/security/login.jsf");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AuthService getAuthService() {
    return authService;
  }

  public void setAuthService(AuthService authService) {
    this.authService = authService;
  }

  protected HttpServletRequest getRequest() {
    return (HttpServletRequest) getFacesContext()
      .getExternalContext()
      .getRequest();
  }

  protected FacesContext getFacesContext() {
    return FacesContext.getCurrentInstance();
  }

  public String getUserLogued() {
    return CurrentUserUtils.getUsername();
  }

  public String getRecoveryCode() {
    return recoveryCode;
  }

  public void setRecoveryCode(String recoveryCode) {
    this.recoveryCode = recoveryCode;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public UserDto getUser() {
    return user;
  }

  public void setUser(UserDto user) {
    this.user = user;
  }


  
}
