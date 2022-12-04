package cu.edu.cujae.pweb.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cu.edu.cujae.pweb.dto.UserAuthenticatedDto;

public class UserPrincipal implements UserDetails {
    private int code;
    private String email;
    private String password;
    private boolean active;
    private String username;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(int code, String email, String password, boolean active, Collection<? extends GrantedAuthority> authorities, String token, String username) {
        this.code = code;
        this.email = email;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
        this.token = token;
        this.username = username;
    }

    public static UserPrincipal create(UserAuthenticatedDto user) {
    	List<GrantedAuthority> authorities;
    	try {
    		Collection<String> roleNames = user.getRoles().stream().map(role -> role.getRole()).collect(Collectors.toList());
    		authorities = AuthorityUtils.createAuthorityList(roleNames.toArray(new String[0]));
		} catch (Exception e) {
			authorities = Collections.
	                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
			
		}
    	return new UserPrincipal(
                user.getCode(),
                user.getEmail(),
                user.getPassword(),
                true,
                authorities,
                user.getToken(),
                user.getUsername()
        );
    }

    public static UserPrincipal create(UserAuthenticatedDto user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


	public void setUsername(String username) {
		this.username = username;
	}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
