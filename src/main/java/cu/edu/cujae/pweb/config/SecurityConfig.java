package cu.edu.cujae.pweb.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // todas las solicitudes deben estar autenticadas excepto las que se definan en este code
        http.authorizeRequests().antMatchers("/javax.faces.resource/**", "/resources/**", "/pages/security/login.jsf", "/pages/errors/**")
        .permitAll()
        .antMatchers("/pages/security/**").hasAnyAuthority("admin")
        .anyRequest().authenticated();
        
        // configurando el login
        http
        .exceptionHandling().accessDeniedPage("/pages/errors/access-denied.jsf");
        
        // logout, cuando se ejecute el logout va para el login
        http.logout().logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/pages/security/login.jsf");
        
        // not needed as JSF 2.2 is implicitly protected against CSRF
        http.csrf().disable();
    }
    
}