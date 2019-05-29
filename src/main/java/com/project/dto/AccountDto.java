package com.project.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "avatar")
	private String avatar;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "role")
	private String role;

	// end

	// region -- Get set --

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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



	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AccountDto() {
		super();
		id = 0;
		email ="";
		password = "";
		avatar = "";
		name = "";
		role = "";
	}
	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static AccountDto convert(Object[] o) {
		AccountDto res = new AccountDto();

		res.setId((Integer) o[0]);
		res.setEmail((String) o[1]);
		res.setPassword((String) o[2]);
		res.setAvatar((String) o[3]);
		res.setName((String) o[4]);
		res.setRole((String) o[5]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<AccountDto> convert(List<Object[]> l) {
		List<AccountDto> res = new ArrayList<AccountDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}