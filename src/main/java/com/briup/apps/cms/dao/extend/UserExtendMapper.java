package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.extend.UserExtend;

public interface UserExtendMapper {
	
	public UserExtend selectByid(Long id);
	
	public List<UserExtend> selectAll();
	
}
