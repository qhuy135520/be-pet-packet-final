package com.petpacket.final_project.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.user.User;

public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	@JsonIgnore
	private String password;
	private String userName;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer userId, String password, String userName,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
		return new UserDetailsImpl(user.getUserId(), user.getPassword(), user.getUserName(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
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

	public Integer getId() {
		return userId;
	}

}
