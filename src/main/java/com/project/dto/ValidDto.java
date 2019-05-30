package com.project.dto;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author ToanNguyen
 *
 */
public class ValidDto {
	// region -- Fields --

	public Double sequence;

	public String message;

	public String portalUserId;

	public String invoiceDataPath;

	@JsonProperty(value = "success")
	private boolean success;

	@JsonProperty(value = "errors")
	private HashMap<String, String> errors;

	// end

	// region -- Get set --

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}



	public HashMap<String, String> getErrors() {
		return errors;
	}

	public void setErrors(HashMap<String, String> errors) {
		this.errors = errors;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ValidDto() {
		success = true;
		errors = new HashMap<String, String>();
		message = "";
	}

	// end
}