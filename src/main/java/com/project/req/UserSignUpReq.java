package com.project.req;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserSignUpReq {
	// region -- Fields --

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "role")
	private String role;

	@JsonProperty(value = "isDelete")
	private Double isDelete;

	@JsonProperty(value = "createBy")
	private Integer createBy;

	@JsonProperty(value = "createOn")
	private Date createOn;

	// end

	// region -- Get set --
	


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Double isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	// end

	// region -- Methods --

	public UserSignUpReq() {
		email = "";
		password = "";
		name = "";
		role = "";
		isDelete = null;
		createBy = 0;
		createOn = null;
	}

	// end
}