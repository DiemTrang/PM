package com.project.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountsDetailDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

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
	public AccountsDetailDto() {
		super();
		id = 0;
		name = "";
		role = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static AccountsDetailDto convert(Object[] o) {
		AccountsDetailDto res = new AccountsDetailDto();

		res.setId((Integer) o[0]);
		res.setName((String) o[1]);
		res.setRole((String) o[2]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<AccountsDetailDto> convert(List<Object[]> l) {
		List<AccountsDetailDto> res = new ArrayList<AccountsDetailDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}