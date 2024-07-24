package com.petpacket.final_project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "\"ExternalLogin\"", schema = "public")
public class ExternalLogin {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(nullable = false, name = "provider")
	private String provider;

	@Column(nullable = false, name = "provider_key")
	private String providerKey;

	public ExternalLogin() {
	}

	public ExternalLogin(Integer id, User user, String provider, String providerKey) {
		super();
		this.id = id;
		this.user = user;
		this.provider = provider;
		this.providerKey = providerKey;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

}
