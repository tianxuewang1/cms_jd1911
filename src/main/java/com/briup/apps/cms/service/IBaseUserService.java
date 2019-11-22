package com.briup.apps.cms.service;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.extend.UserExtend;

@Service
public interface IBaseUserService {
	
	public UserExtend findById(long id);
	
	
	
}
