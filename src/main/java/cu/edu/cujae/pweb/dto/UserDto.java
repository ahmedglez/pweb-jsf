package cu.edu.cujae.pweb.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

  private int code;
  private String username;
  private String password;
  private String email;
  private List<RoleDto> roles;
  private List<String> rolesName = new ArrayList<>();

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

  public List<RoleDto> getRoles() {
    return roles;
  }

  public void setRoles(List<RoleDto> roles) {
    this.roles = roles;
  }

  public List<String> getRolesName() {
    return rolesName;
  }

  public void setRolesName(List<String> rolesName) {
    this.rolesName = rolesName;
  }

  public void rolesName(){
    for(RoleDto r : roles){
      rolesName.add(r.getRole());
    }
  }

}
