/**
 * 
 */
package com.ferreirarubens.authserver.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ferreirarubens.authserver.data.serializer.ProfilePermissionListSerializer;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id_profile")) })
@Table(name = "profile", schema = "access_control")
public class Profile {

	@Id
	private Integer id;

	@Column(name = "nm_profile")
	private String name;

	@Column(name = "nr_hierarchy")
	private int hierarchy;

	@Column(name = "is_active")
	private boolean active;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "profile", orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonSerialize(using = ProfilePermissionListSerializer.class)
	private List<ProfilePermission> profilePermissions = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_profile_type")
	private ProfileType profileType;

	public Profile() {
	}

	public Profile(int id) {
		this.setId(id);
	}

	public Profile(String name, int hierarchy, Collection<ProfilePermission> profilePermissions) {
		this.name = name;
		this.hierarchy = hierarchy;
		this.profilePermissions = new ArrayList<ProfilePermission>(profilePermissions);
	}

	@JsonIgnore
	public Collection<String> getRoles() {
		if (Objects.nonNull(getProfilePermissions())) {
			return getProfilePermissions().stream().map(ProfilePermission::getRoles).flatMap(Collection::stream)
					.sorted().collect(Collectors.toList());
		}
		return null;
	}

	public int getHierarchyAllowed() {
		if (this.hierarchy < 9) {
			return this.hierarchy + 1;
		} else {
			return this.hierarchy;
		}
	}

	public int getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}

	public List<ProfilePermission> getProfilePermissions() {
		return profilePermissions;
	}

	public void setProfilePermissions(List<ProfilePermission> profilePermissions) {
		this.profilePermissions = profilePermissions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}

}
