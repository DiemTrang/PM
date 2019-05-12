package com.project.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dal.ProjectDao;
import com.project.dto.ProjectDto;
import com.project.req.PagingReq;

@Service(value = "projectService")
@Transactional
public class ProjectService {
	// region -- Fields --

	@Autowired
	private ProjectDao projectDao;

	// end

	// region -- Methods --

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<ProjectDto> search(PagingReq req) {
		List<ProjectDto> res = projectDao.search(req);
		return res;
	}

	// end
}