package com.project.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDetailDto {
	// region -- Fields --

	@JsonProperty(value = "id")
	private int id;

	@JsonProperty(value = "project")
	private String project;

	@JsonProperty(value = "nameTask")
	private String nameTask;

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "asign")
	private String asign;

	@JsonProperty(value = "priority")
	private String priority;

	@JsonProperty(value = "dueDate")
	private Integer dueDate;

	@JsonProperty(value = "originalEstimate")
	private Integer originalEstimate;

	@JsonProperty(value = "decription")
	private String decription;

	@JsonProperty(value = "startDate")
	private Date startDate;

	@JsonProperty(value = "endDate")
	private Date endDate;

	@JsonProperty(value = "createBy")
	private String createBy;

	@JsonProperty(value = "createOn")
	private Date createOn;

	@JsonProperty(value = "modifyBy")
	private String modifyBy;

	@JsonProperty(value = "modifyOn")
	private Date modifyOn;

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
	public String getNameTask() {
		return nameTask;
	}
	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Integer getDueDate() {
		return dueDate;
	}
	public void setDueDate(Integer dueDate) {
		this.dueDate = dueDate;
	}
	public Integer getOriginalEstimate() {
		return originalEstimate;
	}
	public void setOriginalEstimate(Integer originalEstimate) {
		this.originalEstimate = originalEstimate;
	}
	public String getDecription() {
		return decription;
	}
	public void setDecription(String decription) {
		this.decription = decription;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyOn() {
		return modifyOn;
	}
	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}
	
	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public TaskDetailDto() {
		super();
		id = 0;
		project = "";
		nameTask = "";
		status = "";
		asign = "";
		priority = "";
		dueDate = null;
		originalEstimate = null;
		decription = "";
		startDate = null;
		endDate = null;
		createBy = "";
		createOn = null;
		modifyBy = "";
		modifyOn = null;
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
		res.setProject((String) o[1]);
		res.setNameTask((String) o[2]);
		res.setStatus((String) o[3]);
		res.setAsign((String) o[4]);
		res.setPriority((String) o[5]);
		res.setDueDate((Integer) o[6]);
		res.setOriginalEstimate((Integer) o[7]);
		res.setDecription((String) o[8]);
		res.setStartDate((Date) o[9]);
		res.setEndDate((Date) o[10]);
		res.setCreateBy((String) o[11]);
		res.setCreateOn((Date) o[12]);
		res.setModifyBy((String) o[13]);
		res.setModifyOn((Date) o[14]);

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