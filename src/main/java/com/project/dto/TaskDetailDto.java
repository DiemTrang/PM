package com.project.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDetailDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "idTask")
	private Integer idTask;

	@JsonProperty(value = "title")
	private String title;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "createOn")
	private Date createOn;

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
	public Integer getIdTask() {
		return idTask;
	}
	public void setIdTask(Integer idTask) {
		this.idTask = idTask;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public TaskDetailDto() {
		super();
		id = 0;
		name ="";
		idTask = 0;
		title = "";
		status = "";
		createOn = null;
	}
	/**
	 * Convert
	 * 
	 * @param o
	 * @return
	 */
	public static TaskDetailDto convert(Object[] o) {
		TaskDetailDto res = new TaskDetailDto();

		res.setId((Integer) o[0]);
		res.setName((String) o[1]);
		res.setIdTask((Integer) o[2]);
		res.setTitle((String) o[3]);
		res.setStatus((String) o[4]);
		res.setCreateOn((Date) o[5]);

		return res;
	}

	/**
	 * Convert
	 * 
	 * @param l
	 * @return
	 */
	public static List<TaskDetailDto> convert(List<Object[]> l) {
		List<TaskDetailDto> res = new ArrayList<TaskDetailDto>();

		for (Object[] o : l) {
			res.add(convert(o));
		}

		return res;
	}

	// end
}