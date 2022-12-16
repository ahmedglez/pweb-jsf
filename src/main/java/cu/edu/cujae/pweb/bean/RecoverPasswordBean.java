package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.service.AuthService;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class RecoverPasswordBean {

  private String email;

  @Autowired
  private AuthService authService;

  @PostConstruct
  public void init() {}

  public void sendRecoveryCode() {
    try {
      authService.sendEmail(email);
      getFacesContext()
        .getExternalContext()
        .redirect(
          getRequest().getContextPath() +
          "/pages/security/recover_password/recovery-code.xhtml"
        );
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
}
