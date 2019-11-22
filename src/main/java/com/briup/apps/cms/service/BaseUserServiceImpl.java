package com.briup.apps.cms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.user;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vmuser.Uservm;

@Service
public class BaseUserServiceImpl implements IBaseUserService{
	
	//级联角色
	@Autowired
	private UserExtendMapper userExtendMapper;
	@Override
	public UserExtend findById(long id) {
		
		UserExtend userExtend = userExtendMapper.selectByid(id);
		return userExtend;
	
	}
	
	 

}
