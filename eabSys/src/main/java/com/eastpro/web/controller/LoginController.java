/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.eastpro.web.controller;

import com.eastpro.web.form.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求)，
 * 真正登录的POST请求由Filter完成,
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/initlogin.eab", method = RequestMethod.GET)
	public String login(Map<String, Object> map) {
        map.put("login", new Login());
		return "main/login";
	}

    //!SimpleEncrypt.encrypt(userId, FormatMan.nullStr(formBean.password), false).equalsIgnoreCase(userObj.getPassword())
}
