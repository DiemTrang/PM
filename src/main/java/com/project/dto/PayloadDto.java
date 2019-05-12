package com.project.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayloadDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "contact")
	private String contact;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "uuid")
	private UUID uuid;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	// end

	// region -- Methods --

	public PayloadDto() {

	}

	// end
}