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
	private Integer modifyBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date modifyOn;
	
	@Column(columnDefinition = "integer")
	private Integer createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
	private Date createdOn;
	
	@Column(columnDefinition = "text")
	private String decription;
	
	@Column(columnDefinition = "text")
	private String board;

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

	public Integer getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}
	

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
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
	
	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	// region -- Methods --

	public TaskReq() {
	}

	// end
}