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
import com.project.dto.SortDto;
import com.project.dto.TaskDetailDto;
import com.project.dto.TaskDto;
import com.project.filter.TaskFilter;
import com.project.model.Task;
import com.project.req.PagingReq;

/**
 * 
 * @author:
 *
 */
@Service(value = "taskDao")
public class TaskDao implements Repository<Task, Integer> {
	// region -- Implements --

	/**
	 * create
	 */
	@Override
	public void create(Task entity) {
		_em.persist(entity);
	}
	/**
	 * upload
	 
	@Override
	public void upload (Task entity) {
		_em.persist(entity);
	}*/

	/**
	 * Read
	 */
	@Override
	public Task read(Integer id) {
		return _em.find(Task.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public Task update(Task entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(Task entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(TaskDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public TaskDao() {
		_path = ZFile.getPath("sql/" + TaskDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TaskDto> search(PagingReq req) {
		List<TaskDto> res = new ArrayList<TaskDto>();

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
			List<Object[]> t = q.getResultList();

			// Convert
			res = TaskDto.convert(t);
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
			TaskFilter filter = TaskFilter.convert(o);
			String name = filter.getName();
			Integer project = filter.getProject();
			Integer asign = filter.getAsign();
			String status = filter.getStatus();

			// Where
			String where = "";
			if (!name.isEmpty()) {
				where += " AND a.title LIKE :name";
			}
			
			if (project != null) {
				where += " AND a.project = :project";
			}
			
			if (asign != null) {
				where += " AND a.asign =  :asign";
			}
			
			if (!status.isEmpty()) {
				where += " AND a.status =  :status";
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
				
				i = where.indexOf(":project");
				if (i > 0) {
					q.setParameter("project", project);
				}
				
				i = where.indexOf(":asign");
				if (i > 0) {
					q.setParameter("asign", asign);
				}
				
				i = where.indexOf(":status");
				if (i > 0) {
					q.setParameter("status", status);
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
	 * Get Account detail
	 * 
	 * @param id
	 * @return
	 */
	public TaskDetailDto getTaskDetail(Integer id) {
		TaskDetailDto res = new TaskDetailDto();

		try {
			String sql = ZFile.read(_path + "getTaskDetail.sql");
			sql += " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = TaskDetailDto.convert(t);
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