package com.project.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bll.TaskService;
import com.project.dto.TaskDetailDto;
import com.project.dto.TaskDto;
import com.project.model.Task;
import com.project.req.PagingReq;
import com.project.req.TaskReq;
import com.project.rsp.BaseRsp;
import com.project.rsp.MultipleRsp;
import com.project.rsp.SingleRsp;

@RestController
@RequestMapping("/task")
public class TaskController {
	// region -- Fields --

	// private static final Logger _log =
	// Logger.getLogger(ProjectController.class.getName());

	@Autowired
	private TaskService taskService;

	// end

	// region -- Methods --

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody PagingReq req) {
		MultipleRsp res = new MultipleRsp();

		try {
			// Handle
			List<TaskDto> t;
			t = taskService.search(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("page", req.getPage());
			data.put("size", req.getSize());
			data.put("total", req.getTotal());
			data.put("data", t);

			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/update-task")
	public ResponseEntity<?> update(@RequestBody TaskReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// Handle
			taskService.update(req);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Read by
	 * 
	 * @param Id
	 * @return
	 */
	@PostMapping("/get-task-detail")
	public ResponseEntity<?> getTaskDetail(@RequestBody Integer id) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			TaskDetailDto t;
			t = taskService.getTaskDetail(id);

			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createTask(@RequestHeader HttpHeaders header, @RequestBody TaskReq req) {
		SingleRsp res = new SingleRsp();

		try {
			String title = req.getTitle();
			String decription = req.getDecription();
			Integer createBy = req.getCreatedById();
			Integer modifyBy = req.getModifyById();
			Task m = new Task();

			m.setTitle(title);
			m.setCreateBy(createBy);
			m.setModifyBy(modifyBy);
			m.setDecription(decription);

			taskService.create(m);

		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	// end
}