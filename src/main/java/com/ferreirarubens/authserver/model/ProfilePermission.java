/**
 * 
 */
package com.ferreirarubens.authserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author rubens.ferreira
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id_profile_permission")) })
@Table(name = "profile_permission")
public class ProfilePermission {

	@Id
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_profile")
	private Profile profile;

	@ManyToOne
	@JoinColumn(name = "id_permission")
	private Permission permission;

	@Column(name = "ck_read")
	private boolean read;

	@Column(name = "ck_insert")
	private boolean insert;

	@Column(name = "ck_update")
	private boolean update;

	@Column(name = "ck_delete")
	private boolean delete;

	private static final String SUFIX_READ = "_READ";

	private static final String SUFIX_DELETE = "_DELETE";

	private static final String SUFIX_UPDATE = "_UPDATE";

	private static final String SUFIX_INSERT = "_INSERT";

	public ProfilePermission() {

	}

	public ProfilePermission(String... profiles) {
		for (String profile : profiles) {
			if (profile.endsWith(SUFIX_READ))
				read = true;
			else if (profile.endsWith(SUFIX_INSERT))
				insert = true;
			else if (profile.endsWith(SUFIX_UPDATE))
				update = true;
			else if (profile.endsWith(SUFIX_DELETE))
				delete = true;
		}
	}

	public ProfilePermission(Permission permission) {
		this.permission = permission;
	}

	public ProfilePermission(Permission permission, Profile profile, boolean read, boolean insert, boolean update,
			boolean delete) {
		this.permission = permission;
		this.profile = profile;
		this.read = read;
		this.insert = insert;
		this.update = update;
		this.delete = delete;
	}

	public ProfilePermission(Permission permission, boolean read, boolean insert, boolean update, boolean delete) {
		this.permission = permission;
		this.read = read;
		this.insert = insert;
		this.update = update;
		this.delete = delete;
	}

	public ProfilePermission(Permission permission, boolean read) {
		this.permission = permission;
		this.read = read;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isInsert() {
		return insert;
	}

	public void setInsert(boolean insert) {
		this.insert = insert;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<String>(5);

		String permission = this.permission.getName();

		roles.add(permission);

		if (this.permission.isCrud()) {

			if (isRead())
				roles.add(getProfileRead(permission));

			if (isInsert())
				roles.add(getProfileInsert(permission));

			if (isUpdate())
				roles.add(getProfileUpdate(permission));

			if (isDelete())
				roles.add(getProfileDelete(permission));

		}
		return roles;
	}

	private String getProfileRead(String permission) {
		return permission + SUFIX_READ;
	}

	private String getProfileInsert(String permission) {
		return permission + SUFIX_INSERT;
	}

	private String getProfileUpdate(String permission) {
		return permission + SUFIX_UPDATE;
	}

	private String getProfileDelete(String permission) {
		return permission + SUFIX_DELETE;
	}

	@Transient
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	@Transient
	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return getRoles().toString();
	}
}

