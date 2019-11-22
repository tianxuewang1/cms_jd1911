package com.briup.apps.cms.vmuser;

import java.util.List;

/**
 * @program: cms_jd1911
 * @description: 用户角色
 * @author: charles
 * @create: 2019-11-17 21:21
 **/
//用户id和角色绑定
public class UserRoleVM {
    private Long id;
    private List<Long> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

	@Override
	public String toString() {
		return "UserRoleVM [id=" + id + ", roles=" + roles + "]";
	}
    
}
