/**
 * 
 */
package com.ferreirarubens.authserver.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @auhtor Ferreira Rubens <rubensdefrancaferreira@gmail.com>
 *
 */
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id_profile_type")) })
@Table(name = "profile_type", schema = "access_control")
public class ProfileType {

	@Column(name = "tp_category", nullable = false, length = 50)
	private String category;

	@Id
	private Integer id;

	@Column(name = "nm_profile_type")
	private String name;

	@Column(name = "ds_profile_type")
	private String description;

	public ProfileType() {
	}

	public ProfileType(int id) {
		this.setId(id);
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
