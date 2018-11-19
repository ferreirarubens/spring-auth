package com.ferreirarubens.authserver.security.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ferreirarubens.authserver.model.User;

/**
 * 
 * @author rubens.ferreira
 *
 */
public class LoggedUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;

	private String login;

	private String password;
	
	public LoggedUser(User user) {
		this.user = user;
		this.login = user.getLogin();
		this.password = user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		getUser().getProfile().getRoles().stream()
				.forEach(profile -> authorities.add(new SimpleGrantedAuthority(profile)));
		
		return authorities;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.login;
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
		return true;
	}

}
