package com.briup.apps.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.user;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vmuser.UserRoleVM;
import com.briup.apps.cms.vmuser.Uservm;

@Service
public interface IUserService {
	
	public List<user> findAll();
		
	public List<UserExtend> cascadeRoleFindAll();
	
	public void saveOrUpdate(user baseUser);
	
	public void deleteById(Long id);
	
	public void setRoles(UserRoleVM userRole);
	
	 public user login(Uservm userVM) throws CustomerException ;
}
