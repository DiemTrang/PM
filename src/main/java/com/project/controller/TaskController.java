package com.project.controller;

import java.util.Date;
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

import com.project.bll.ProjectService;
import com.project.dto.ProjectDto;
import com.project.model.Project;
import com.project.req.PagingReq;
import com.project.req.ProjectNewReq;
import com.project.req.ProjectReq;
import com.project.rsp.BaseRsp;
import com.project.rsp.MultipleRsp;
import com.project.rsp.SingleRsp;

@RestController
@RequestMapping("/task")
public class TaskController {
	// region -- Fields --

	//private static final Logger _log = Logger.getLogger(ProjectController.class.getName());

	@Autowired
	private ProjectService taskService;

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
			List<ProjectDto> t;
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

	@PostMapping("/update-project")
	public ResponseEntity<?> update(@RequestBody ProjectReq req) {
		BaseRsp res = new BaseRsp();

		try {
			// Handle
			taskService.update(req);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestHeader HttpHeaders header, @RequestBody ProjectNewReq req) {
		SingleRsp res = new SingleRsp();

		try {
			String title = req.getTitle();
			Boolean isDeleted = req.getIsDeleted();
			Integer createBy = req.getCreateBy();
			Date createOn = req.getCreateOn();

			Project m = new Project();

			m.setTitle(title);
			m.setDeleted(isDeleted);
			m.setCreateBy(createBy);
			m.setCreateOn(createOn);

			taskService.create(m);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}