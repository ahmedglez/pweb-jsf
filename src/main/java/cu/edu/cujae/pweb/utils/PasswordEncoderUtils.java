package cu.edu.cujae.pweb.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtils implements PasswordEncoder {

  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Override
  public String encode(CharSequence rawPassword) {
    return encoder.encode(rawPassword);
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }

  @Override
  public boolean upgradeEncoding(String encodedPassword) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public boolean isPasswordValid(String rawPassword, String encodedPassword) {
    return encoder.matches(rawPassword, encodedPassword);
  }
}
