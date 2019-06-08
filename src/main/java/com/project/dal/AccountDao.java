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
import com.project.dto.AccountDto;
import com.project.dto.AccountsDetailDto;
import com.project.dto.SortDto;
import com.project.filter.AccountFilter;
import com.project.model.Users;
import com.project.req.PagingReq;

/**
 * 
 * @author:
 *
 */
@Service(value = "_accountDao")
public class AccountDao implements Repository<Users, Integer> {
	// region -- Implements --

	/**
	 * Create
	 */
	@Override
	public void create(Users entity) {
		_em.persist(entity);
	}

	/**
	 * Read
	 */
	@Override
	public Users read(Integer id) {
		return _em.find(Users.class, id);
	}

	/**
	 * Update
	 */
	@Override
	public Users update(Users entity) {
		return _em.merge(entity);
	}

	/**
	 * Delete
	 */
	@Override
	public void delete(Users entity) {
		_em.remove(entity);
	}

	// end

	// region -- Fields --

	@Autowired
	private EntityManager _em;

	private String _path;

	private String _sql;

	private static final Logger _log = Logger.getLogger(AccountDao.class.getName());

	// end

	// region -- Methods --

	/**
	 * Initialize
	 */
	public AccountDao() {
		_path = ZFile.getPath("sql/" + AccountDao.class.getSimpleName());
		_sql = ZFile.read(_path + "_sql.sql");
	}

	/**
	 * Get by
	 * 
	 * @param id
	 * @return
	 */
	public AccountDto getBy(Integer id) {
		AccountDto res = new AccountDto();

		try {
			String sql = _sql + " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = AccountDto.convert(t);
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
	 * Get by
	 * 
	 * @param userId
	 * @return
	 */
	public AccountDto signIn(String userName, String password) {
		AccountDto res = new AccountDto();

		try {
			String sql = _sql;

				sql += " WHERE a.email = :userName";
			

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("userName", userName);
			q.setParameter("password", password);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = AccountDto.convert(t);
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
	 * Select credit note number exist in db.
	 * 
	 * 
	 * /** Search by
	 * 
	 * @param req
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AccountDto> search(PagingReq req) {
		List<AccountDto> res = new ArrayList<AccountDto>();

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

				if ("status".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.status__c " + direction;
				}

				if ("customer".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.customer__c " + direction;
				}

				if ("createdDate".equals(field)) {
					if (!orderBy.isEmpty()) {
						orderBy += ",";
					}
					orderBy += " a.createddate " + direction;
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
			res = AccountDto.convert(t);
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
			AccountFilter filter = AccountFilter.convert(o);
			String name = filter.getName();

			// Where
			String where = "";
			if (!name.isEmpty()) {
				where += " AND a.name = :name";
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
	public AccountsDetailDto getAccountsDetail(Integer id) {
		AccountsDetailDto res = new AccountsDetailDto();

		try {
			String sql = ZFile.read(_path + "getAccountsDetail.sql");
			sql += " WHERE a.id = :id";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("id", id);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = AccountsDetailDto.convert(t);
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
	 * 
	 * @param email
	 * @return
	 */
	
	public AccountDto checkExist(String userName) {
		AccountDto res = new AccountDto();
		
		try {
			String sql = _sql;
			sql += " WHERE a.email = :userName";

			// Execute
			Query q = _em.createNativeQuery(sql);
			q.setParameter("userName", userName);
			Object[] t = (Object[]) q.getSingleResult();

			// Convert
			res = AccountDto.convert(t);
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