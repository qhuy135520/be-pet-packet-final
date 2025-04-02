package com.petpacket.final_project.services.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.Enum.EGender;
import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.Enum.EStatus;
import com.petpacket.final_project.entities.user.User;

import jakarta.transaction.Transactional;

@Transactional
public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String username;
	private String email;
	@JsonIgnore
	private String password;
	private ECity address;
	private String name;
	private EGender gender;
	private String phone;
	private EStatus status;
	private String picture;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Integer userId, String username, String email, String password, ECity address,
			String name, EGender gender, String phone, EStatus status, String picture,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.status = status;
		this.picture = picture;
		this.authorities = authorities;

	}

	public static UserDetailsImpl build(User user) {
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName().name());
		return new UserDetailsImpl(user.getUserId(), user.getUsername(), user.getEmail(), user.getPassword(),
				user.getCity(), user.getName(), user.getGender(), user.getPhone(), user.getStatus(),
				user.getUserPicture(), Collections.singletonList(authority));
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
		return username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public ECity getAddress() {
		return address;
	}

	public void setAddress(ECity address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EGender getGender() {
		return gender;
	}

	public void setGender(EGender gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.status.name().equals("ACTIVE") ? true : false;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}



}
