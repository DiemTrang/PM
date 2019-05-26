package com.project.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectDetailDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "project")
	private String project;

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "name")
	private String name;

	// end

	// region -- Get set --

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ProjectDetailDto() {
		super();
		id = 0;
		project = "";
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ProjectDetailDto convert(Object[] o) {
		ProjectDetailDto res = new ProjectDetailDto();

		res.setId((Integer) o[0]);
		res.setProject((String) o[1]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ProjectDetailDto> convert(List<Object[]> l) {
		List<ProjectDetailDto> res = new ArrayList<ProjectDetailDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}