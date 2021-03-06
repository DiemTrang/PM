package com.project.bll;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dal.AccountDao;
import com.project.dto.AccountDto;
import com.project.dto.AccountsDetailDto;
import com.project.model.Users;
import com.project.req.PagingReq;

/**
 * 
 * @author:
 *
 */
@Service(value = "_accountService")
@Transactional
public class AccountService {
	// region -- Fields --

	@Autowired
	private AccountDao _accountDao;

	// end

	// region -- Methods --

	/**
	 * Create
	 * 
	 * @param m
	 */
	public void create(Users m) {
		Date now = new Date();

		m.setDeleted(false);
		m.setCreateOn(now);

		_accountDao.create(m);
	}

	/**
	 * Update CreditNote
	 * 
	 * @param req
	 * @return
	 */
	public String update(Users req) {
		String res = "";

		// Get data
		Integer id = req.getId();

		// String status = req.getStatus();

		// Handle
		Users m = _accountDao.read(id);
		if (m == null) {
			res = "Id does not exist";
		} else {
			// m.setStatus(status);

			_accountDao.update(m);
		}

		return res;
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public AccountDto read(int id) {
		AccountDto res = _accountDao.getBy(id);
		return res;
	}

	/**
	 * Search by
	 * 
	 * @param req
	 * @return
	 */
	public List<AccountDto> search(PagingReq req) {
		List<AccountDto> res = _accountDao.search(req);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public AccountDto signIn(String userName, String password) {
		AccountDto res = _accountDao.signIn(userName, password);
		return res;
	}

	/**
	 * Read by
	 * 
	 * @param id
	 * @return
	 */
	public AccountsDetailDto getAccountsDetail(int id) {
		return _accountDao.getAccountsDetail(id);
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	public AccountDto checkExist(String userName) {
		return _accountDao.checkExist(userName);
	}
	// end
}