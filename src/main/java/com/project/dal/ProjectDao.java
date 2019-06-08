package com.project.dal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.common.ZConfig;
import com.project.common.ZFile;
import com.project.dto.ProjectDetailDto;
import com.project.dto.ProjectDto;
import com.project.dto.SortDto;
import com.project.filter.ProjectFilter;
import com.project.model.Project;
import com.project.req.PagingReq;

/**
 * 
 * @author:
 *
 */
@Service(value = "projectDao")
public class ProjectDao implements Repository<Project, Integer> {
	// region -- Implements --

	/**
	 * create
	 */
	@Override
	public void create(Project entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public Project read(Integer id) {
		return _em.find(Project.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public Project update(Project entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(Project entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(ProjectDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public ProjectDao() {
		_path = ZFile.getPath("sql/" + ProjectDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProjectDto> search(PagingReq req) {
		List<ProjectDto> res = new ArrayList<ProjectDto>();

		try {
			// Get data
			Object filter = req.getFilter();
			int page = req.getPage();
			int size = req.getSize();
			List<SortDto> sort = req.getSort();
			int offset = (page - 1) * size;

			// Order by
			String orderBy = "";
			for (SortDto o : sort) {
				String field = o.getField();
				String direction = o.getDirection();

				if ("id".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.id " + direction;
				}

				if ("name".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.name " + direction;
				}
			}

			if (!orderBy.isEmpty()) {
				orderBy = " ORDER BY " + orderBy;
			}

			// Execute to count all
			int i = _sql.indexOf("FROM");
			String sql = "SELECT COUNT(*) " + _sql.substring(i);
			String limit = "";
			Query q = createQuery(sql, filter, limit);
			BigInteger total = (BigInteger) q.getSingleResult();
			req.setTotal(total.longValue());

			// Execute to search
			sql = _sql;
			limit = orderBy;
			if (req.isPaging()) {
				limit += " OFFSET " + offset + " LIMIT " + size;
			}
			q = createQuery(sql, filter, limit);
			List<Object[]> l = q.getResultList();

			// Convert
			res = ProjectDto.convert(l);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	/**
	 * Create query to prevent SQL injection
	 * 
	 * @param sql
	 * @param o
	 * @return
	 */
	private Query createQuery(String sql, Object o, String limit) {
		Query q = null;

		try {
			ProjectFilter filter = ProjectFilter.convert(o);
			String name = filter.getName();
			String role = filter.getRole();

			// Where
			String where = "";
			if (!name.isEmpty()) {
				where += " AND a.name = :name";
			}
			
			if (!role.isEmpty()) {
				where += " AND a.role =  :role";
			}

			// Replace first
			if (!where.isEmpty()) {
				where = where.replaceFirst("AND", "WHERE");
			}

			q = _em.createNativeQuery(sql + where + limit);

			// Set parameter
			if (!where.isEmpty()) {
				int i = where.indexOf(":name");
				if (i > 0) {
					q.setParameter("name", name);
				}
			}
			if (!where.isEmpty()) {
				int i = where.indexOf(":role");
				if (i > 0) {
					q.setParameter("role", role);
				}
			}
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return q;
	}

	/**
	 * Get Project detail
	 * 
	 * @param id
	 * @return
	 */
	public ProjectDetailDto getProjectDetail(Integer id) {
		ProjectDetailDto res = new ProjectDetailDto();

		try {
			String sql = ZFile.read(_path + "getProjectDetail.sql");
			sql += " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = ProjectDetailDto.convert(t);
		} catch (Exception ex) {
			if (ZConfig._printTrace) {
				ex.printStackTrace();
			}
			if (ZConfig._writeLog) {
				_log.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}

		return res;
	}

	// end
}