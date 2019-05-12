package com.project.controller;

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
import com.project.common.Utils;
import com.project.dto.PayloadDto;
import com.project.rsp.BaseRsp;
import com.project.rsp.SingleRsp;

@RestController
@RequestMapping("/portal-customer-limit")
public class Project<ExpenseReq> {
	// region -- Fields --

	@Autowired
	private ProjectService projectService;

	// end

	// region -- Methods --

	// end
}