package com.project.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bll.AccountService;
import com.project.dto.AccountDto;
import com.project.dto.AccountsDetailDto;
import com.project.model.Users;
import com.project.req.PagingReq;
import com.project.req.UserSignInReq;
import com.project.req.UserSignUpReq;
import com.project.rsp.MultipleRsp;
import com.project.rsp.SingleRsp;

@RestController
@RequestMapping("/account")
public class AccountController {

	// region -- Fields --

	@Autowired
	private AccountService accountService;

// private static final Logger _log =
// Logger.getLogger(AccountController.class.getName());

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
			List<AccountDto> tmp;
			tmp = accountService.search(req);

			// Set data
			Map<String, Object> data = new LinkedHashMap<>();
			data.put("page", req.getPage());
			data.put("size", req.getSize());
			data.put("total", req.getTotal());
			data.put("data", tmp);

			res.setResult(data);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Sign In
	 * 
	 * @return
	 */
	@PostMapping("/sign-in")
	public ResponseEntity<?> signIn(@RequestBody UserSignInReq req) {
		SingleRsp res = new SingleRsp();

		try {
			String userName = req.getUserName();
			String password = req.getPassword();

			AccountDto m = accountService.signIn(userName, password);
			if (m.getEmail() == null || m.getEmail() == "" || m.getPassword() == null || m.getId() == 0) {
				res.setError("Error");
			} else {
				res.setResult(m);
			}
		} catch (AuthenticationException e) {
			res.setError("Unauthorized/Invalid email or password!");
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
	@PostMapping("/read")
	public ResponseEntity<?> read(@RequestBody int Id) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			AccountDto t;
			t = accountService.read(Id);

			res.setResult(t);
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
	@PostMapping("/get-accounts-detail")
	public ResponseEntity<?> getAccountsDetail(@RequestBody Integer id) {
		SingleRsp res = new SingleRsp();

		try {
			// Handle
			AccountsDetailDto t;
			t = accountService.getAccountsDetail(id);

			res.setResult(t);
		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	/**
	 * Create Account
	 * 
	 * @param header
	 * @param req
	 * @return
	 * @author TrangNguyen 2018-Dec-20 11:15
	 */
	@PostMapping("/create-account")
	public ResponseEntity<?> createAccount(@RequestHeader HttpHeaders header, @RequestBody UserSignUpReq req) {
		SingleRsp res = new SingleRsp();

		try {
			String email = req.getEmail();
			String password = req.getPassword();
			String name = req.getName();
			String role = req.getRole();

			AccountDto check = accountService.checkExist(email);

			if (check.getEmail() != null && check.getEmail() != "" ) {
				res.setError("This email already exists");
			} else {

				Users m = new Users();

				m.setEmail(email);
				m.setPassword(password);
				m.setName(name);
				m.setRole(role);

				accountService.create(m);
			}

		} catch (Exception ex) {
			res.setError(ex.getMessage());
		}

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	// end
}