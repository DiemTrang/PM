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
public class TaskFilter {
	// region -- Fields --

	@JsonProperty(value = "name")
	private String name;
	
	@JsonProperty(value = "project")
	private Integer project;
	
	@JsonProperty(value = "asign")
	private Integer asign;

	// region -- Get set --

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getProject() {
		return project;
	}

	public void setProject(Integer project) {
		this.project = project;
	}

	public Integer getAsign() {
		return asign;
	}

	public void setAsign(Integer asign) {
		this.asign = asign;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public TaskFilter() {	
		name = "";
		project = null;
		asign = null;
	}

	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static TaskFilter convert(Object o) {
		TaskFilter res = new TaskFilter();

		try {
			ObjectMapper mapper = new ObjectMapper();
			String s = mapper.writeValueAsString(o);

			try {
				res = mapper.readValue(s, TaskFilter.class);
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