package com.project.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dal.ProjectDao;
import com.project.dto.ProjectDto;
import com.project.model.Project;
import com.project.req.PagingReq;
import com.project.req.ProjectReq;

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
	
	public String update(@RequestBody ProjectReq req) {
		String res = "";

		// Get data
		Integer id = req.getId();
		String title = req.getTitle();

		// Handle
		Project m = projectDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			m.setTitle(title);
			m.setModifyOn(new Date());

			projectDao.update(m);
		}

		return res;
	}
	

	public void create(Project m) {
		Date now = new Date();

		m.setDeleted(false);
		m.setCreateOn(now);

		projectDao.create(m);
	}

	// end
}