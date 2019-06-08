package com.project.filter;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.common.ZConfig;

/**
 * 
 * @author:
 *
 */
public class AccountFilter {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "role")
	private String role;


	// region -- Get set --

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
	public AccountFilter() {	
		name = "";
		role="";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static AccountFilter convert(Object o) {
		AccountFilter res = new AccountFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, AccountFilter.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
		}

		return res;
	}

	// end
}