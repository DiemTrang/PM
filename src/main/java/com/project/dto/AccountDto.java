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
	private int avatar;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "role")
	private int role;

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

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
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
		avatar = 0;
		name = "";
		role = 0;
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
		res.setAvatar((Integer) o[3]);
		res.setName((String) o[4]);
		res.setRole((Integer) o[5]);

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