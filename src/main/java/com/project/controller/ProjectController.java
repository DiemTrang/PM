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

import com.project.bll.ProjectService;
import com.project.dto.ProjectDetailDto;
import com.project.dto.ProjectDto;
import com.project.model.Project;
import com.project.req.PagingReq;
import com.project.req.ProjectNewReq;
import com.project.req.ProjectReq;
import com.project.rsp.BaseRsp;
import com.project.rsp.MultipleRsp;
import com.project.rsp.SingleRsp;

@RestController
@RequestMapping("/project")
public class ProjectController {
	// region -- Fields --

	// private static final Logger _log =
	// Logger.getLogger(ProjectController.class.getName());

	@Autowired
	private ProjectService projectService;

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
			t = projectService.search(req);

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
			projectService.update(req);
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

			Project m = new Project();

			m.setTitle(title);
			m.setDeleted(isDeleted);

			projectService.create(m);
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
	@PostMapping("/get-project-detail")
	public ResponseEntity<?> getProjectDetail(@RequestBody Integer id) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			ProjectDetailDto t;
			t = projectService.getProjectDetail(id);

			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}