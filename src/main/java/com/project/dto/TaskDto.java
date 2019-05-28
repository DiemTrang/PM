package com.project.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "project")
	private String project;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "asign")
	private String asign;

	// end

	// region -- Get set --

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAsign() {
		return asign;
	}

	public void setAsign(String asign) {
		this.asign = asign;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public TaskDto() {
		super();

		id = 0;
		project = "";
		name = "";
		status = "";
		asign = "";
		
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static TaskDto convert(Object[] o) {
		TaskDto res = new TaskDto();

		res.setId((Integer) o[0]);
		res.setProject((String) o[1]);
		res.setName((String) o[2]);
		res.setStatus((String) o[3]);
		res.setAsign((String) o[4]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<TaskDto> convert(List<Object[]> l) {
		List<TaskDto> res = new ArrayList<TaskDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}