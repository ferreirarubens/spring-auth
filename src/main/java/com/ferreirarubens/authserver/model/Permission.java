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
 **/
@Entity
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "id_permission")) })
@Table(name = "permission", schema = "access_control")
public class Permission {

	@Id
	private Integer id;

	@Column(name = "nm_permission")
	private String name;

	@Column(name = "is_crud")
	private boolean crud;

	public Permission() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Permission(int id) {
		setId(id);
	}

	public Permission(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCrud() {
		return crud;
	}

	public void setCrud(boolean crud) {
		this.crud = crud;
	}

}
