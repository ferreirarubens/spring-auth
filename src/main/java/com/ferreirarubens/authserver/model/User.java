/**
 * 
 */
package com.ferreirarubens.authserver.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ferreirarubens.authserver.security.model.interfaces.Authenticated;

/**
 * @author rubens.ferreira
 *
 */
@Entity
@Table(name = "users", schema = "access_control")
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id_user")) })
public class User implements Authenticated {

	@Id
	private long id;

	@Column(name = "ds_login")
	private String login;

	@Column(name = "ds_password")
	private String password;

	@Column(name = "nm_user")
	private String name;

	@ManyToOne
	@JoinColumn(name = "id_profile")
	private Profile profile;

	public User() {
	}

	public User(String login, String password, String name, Profile profile) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.profile = profile;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}