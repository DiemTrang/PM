package com.project.req;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author
 *
 */
public class UploadReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// end

	// region -- Methods --

	public UploadReq() {
		id = null;
	}

	// end
}