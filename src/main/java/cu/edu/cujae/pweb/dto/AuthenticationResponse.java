package cu.edu.cujae.pweb.dto;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

  private static final long serialVersionUID = -8091879091924046844L;
  private final String jwttoken;
  private final String refreshToken;

  public AuthenticationResponse(String jwttoken, String refreshToken) {
    this.jwttoken = jwttoken;
    this.refreshToken = refreshToken;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getJwttoken() {
    return jwttoken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }
}
