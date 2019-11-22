package com.briup.apps.cms.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.user;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.service.BaseUserServiceImpl;
import com.briup.apps.cms.service.IBaseUserService;
import com.briup.apps.cms.service.IUserService;
import com.briup.apps.cms.service.UserServiceImpl;
import com.briup.apps.cms.utils.JwtTokenUtil;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;
import com.briup.apps.cms.vmuser.Uservm;

import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/user")
public class BaseUserController2 {

	@Autowired
	private IBaseUserService iBaseUserService;
	@Autowired
	private IUserService iuserService; 
	
	@ApiOperation(value ="登录")
	@PostMapping("login")
	public Message login(@RequestBody Uservm userVM) {
		// 1. 认证用户的用户名和密码
		// 3. 如果登录失败
		user user = iuserService.login(userVM);
		// 2. 如果登录成功产生token,将token缓存起来，返回
		 String token = JwtTokenUtil.createJWT(user.getId(), user.getUsername());
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		return MessageUtil.success(map);
	}

	@ApiOperation(value = "通过token获取用户的基本信息")
	@GetMapping("info")
	public Message info(String token) {
		// 1. 通过token获取用户信息 {id,use,gender,roles:[]}
		 long id = Long.parseLong(JwtTokenUtil.getUserId(token,JwtTokenUtil.base64Secret));
		 UserExtend userExtend = iBaseUserService.findById(id);
	        return MessageUtil.success(userExtend);
		
		
	}

	@PostMapping("logout")
	public Message logout() {
		// 1. 登录， token从缓存中移除掉
		return MessageUtil.success("退出成功");
	}
	
	
	
	
}
