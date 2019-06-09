package com.project.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dal.TaskDao;
import com.project.dto.TaskDetailDto;
import com.project.dto.TaskDto;
import com.project.model.Task;
import com.project.req.PagingReq;
import com.project.req.TaskReq;

@Service(value = "taskService")
@Transactional
public class TaskService {
	// region -- Fields --

	@Autowired
	private TaskDao taskDao;

	// end

	// region -- Methods --

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<TaskDto> search(PagingReq req) {
		List<TaskDto> res = taskDao.search(req);
		return res;
	}

	public String update(@RequestBody TaskReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();
		String decription = req.getDecription();

		// Handle
		Task m = taskDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setModifyOn(new Date());
			m.setDecription(decription);

			taskDao.update(m);
		}

		return res;
	}

	public void create(Task m) {
		Date now = new Date();

		m.setDeleted(false);
		m.setCreateOn(now);

		taskDao.create(m);
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public TaskDetailDto getTaskDetail(int id) {
		return taskDao.getTaskDetail(id);
	}

	// end
}