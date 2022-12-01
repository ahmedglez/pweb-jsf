package cu.edu.cujae.pweb.dto;


import java.util.Collection;

public class UserDto {

  private int code;
  private String username;
  private String password;
  private String email;
  private Collection<RoleDto> roles;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public Collection<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(Collection<RoleDto> roles) {
    this.roles = roles;
  }
}
