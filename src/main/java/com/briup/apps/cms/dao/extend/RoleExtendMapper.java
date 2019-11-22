package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;

public interface RoleExtendMapper {
	
	public List<Role> selectByUserId(Long id);
	public List<BaseRoleExtend> cascadePrivilegeFindAll();
	
}
