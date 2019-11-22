package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.vmuser.PrivilegeTree;

public interface BasePrivilegeExtendMapper {
    List<PrivilegeTree> selectAll();

    List<PrivilegeTree> selectByParentId(long id);

    List<PrivilegeTree> selectByRoleId(long id);
    
    List<Privilege> findByUserId(long id);
}
