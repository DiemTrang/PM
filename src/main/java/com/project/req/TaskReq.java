package com.project.req;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskReq {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@Column(columnDefinition = "varchar(64)")
	private String title;

	@Column(columnDefinition = "integer")
	private Integer modifyById;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date modifyOn;
	
	@Column(columnDefinition = "integer")
	private Integer createdById;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date createdOn;
	
	@Column(columnDefinition = "text")
	private String decription;
	
	@Column(columnDefinition = "text")
	private String originalEstimate;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getModifyById() {
		return modifyById;
	}

	public void setModifyBy(Integer modifyById) {
		this.modifyById = modifyById;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}
	

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}


	// region -- Methods --


	
	public TaskReq() {
	}

	// end
}