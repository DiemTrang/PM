package com.project.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectDto {
	// region -- Fields --
	
	@JsonProperty(value = "id")
	private Integer id;

	@JsonProperty(value = "title")
	private String title;
	
	// end

	// region -- Get set --
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	// end

	// region -- Methods --


	/**
	 * Initialize
	 */
	public ProjectDto() {
		super();
		
		title ="";
		id = 0;
	}
	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static ProjectDto convert(Object[] o) {
		ProjectDto res = new ProjectDto();

		res.setId((Integer) o[0]);
		res.setTitle((String) o[1]);
		
		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<ProjectDto> convert(List<Object[]> l) {
		List<ProjectDto> res = new ArrayList<ProjectDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}