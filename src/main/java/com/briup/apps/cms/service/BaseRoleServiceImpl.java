package com.briup.apps.cms.service;


import com.briup.apps.cms.bean.Role;
import com.briup.apps.cms.bean.RoleExample;
import com.briup.apps.cms.bean.Role_privilege;
import com.briup.apps.cms.bean.Role_privilegeExample;
import com.briup.apps.cms.bean.extend.BaseRoleExtend;
import com.briup.apps.cms.dao.RoleMapper;
import com.briup.apps.cms.dao.Role_privilegeMapper;
import com.briup.apps.cms.dao.extend.RoleExtendMapper;
import com.briup.apps.cms.service.IBaseRoleService;
import com.briup.apps.cms.utils.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: cms_jd1911
 * @description: 角色实现类
 * @author: charles
 * @create: 2019-11-16 15:55
 **/
@Service
public class BaseRoleServiceImpl implements IBaseRoleService {
    @Autowired
    private RoleMapper baseRoleMapper;
    @Autowired
    private RoleExtendMapper baseRoleExtendMapper;
    @Autowired
    private Role_privilegeMapper baseRolePrivilegeMapper;

    @Override
    public void authorization(long roleId, List<Long> privilegeIds) {
        // 根据roleid查询出所有的权限
    	Role_privilegeExample example = new Role_privilegeExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
         List<Role_privilege> list = baseRolePrivilegeMapper.selectByExample(example);
        // 将list转换为privilegeIDs的集合
        List<Long> old_privilegeIds = new ArrayList<>();
        for(Role_privilege rp : list){
            old_privilegeIds.add(rp.getPrivilegeId());
        }
        // 依次判断privilegeIds 是否存在old_privilegeIds，如果不在则插入
        for(long privilegeId : privilegeIds){
            if (!old_privilegeIds.contains(privilegeId)) {
            	Role_privilege rp = new Role_privilege();
                rp.setRoleId(roleId);
                rp.setPrivilegeId(privilegeId);
                baseRolePrivilegeMapper.insert(rp);
            }
        }
        // 依次判断 是否存在old_privilegeIds 是否存在privilegeIds，如果不存在删除
        for(long privilegeId: old_privilegeIds){
            if(!privilegeIds.contains(privilegeId)){
                // 根据privilegeId 从桥表中删除
                example.clear();
                example.createCriteria()
                        .andRoleIdEqualTo(roleId)
                        .andPrivilegeIdEqualTo(privilegeId);
                baseRolePrivilegeMapper.deleteByExample(example);
            }
        }

    }

    @Override
    public List<Role> findAll() {

        return baseRoleMapper.selectByExample(new RoleExample());
    }

    @Override
    public List<BaseRoleExtend> cascadePrivilegeFindAll() {
        return baseRoleExtendMapper.cascadePrivilegeFindAll();
    }

    @Override
    public void saveOrUpdate(Role baseRole) throws CustomerException {
        if(baseRole.getId()!=null){
            baseRoleMapper.updateByPrimaryKey(baseRole);
        } else {
            baseRoleMapper.insert(baseRole);
        }
    }

    @Override
    public void deleteById(long id) throws CustomerException {
        Role role = baseRoleMapper.selectByPrimaryKey(id);
        if(role == null){
            throw new CustomerException("要删除的角色不存在");
        }
        baseRoleMapper.deleteByPrimaryKey(id);
    }
}
