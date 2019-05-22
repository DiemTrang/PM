package com.project.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bll.AccountService;
import com.project.dto.AccountDto;
import com.project.req.PagingReq;
import com.project.req.UserSignInReq;
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
	// end
}