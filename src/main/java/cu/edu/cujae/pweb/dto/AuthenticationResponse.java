package cu.edu.cujae.pweb.dto;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

  private String jwttoken;
  private String token;

  public AuthenticationResponse() {}

  public AuthenticationResponse(String jwttoken, String token) {
    this.jwttoken = jwttoken;
    this.token = token;
  }

  public String getJwttoken() {
    return jwttoken;
  }

  public void setJwttoken(String jwttoken) {
    this.jwttoken = jwttoken;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }



}
