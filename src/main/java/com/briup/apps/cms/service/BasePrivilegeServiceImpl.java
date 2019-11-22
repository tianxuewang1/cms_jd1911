package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Privilege;
import com.briup.apps.cms.bean.PrivilegeExample;
import com.briup.apps.cms.dao.PrivilegeMapper;
import com.briup.apps.cms.dao.extend.BasePrivilegeExtendMapper;
import com.briup.apps.cms.service.IBasePrivilegeService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vmuser.PrivilegeTree;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: cms_jd1911
 * @description: 权限控制实现类
 * @author: charles
 * @create: 2019-11-16 15:54
 **/
@Service
public class BasePrivilegeServiceImpl implements IBasePrivilegeService {
    @Autowired
    private PrivilegeMapper basePrivilegeMapper;
    @Autowired
    private BasePrivilegeExtendMapper basePrivilegeExtendMapper;
    
    //查找所有权限
    public List<Privilege> findAll() {
        return basePrivilegeMapper.selectByExample(new PrivilegeExample());
    }

    //保存权限
    @Override
    public void saveOrUpdate(Privilege privilege) throws CustomerException {
        if(privilege.getId()!=null){
            basePrivilegeMapper.updateByPrimaryKey(privilege);
        } else {
            basePrivilegeMapper.insert(privilege);
        }
    }
    
   // 找寻所有权限通过父id找，父id有则通过父亲id找，如果父亲id没有，则通过父id等于
    // null找。
    
    @Override
    public List<Privilege> findByParentId(Long parentId) {
    	
        PrivilegeExample example = new PrivilegeExample();
        if(parentId == null){
            example.createCriteria().andParentIdIsNull();
        } else {
            example.createCriteria().andParentIdEqualTo(parentId);
        }
        return basePrivilegeMapper.selectByExample(example);
    }

    @Override
    public List<PrivilegeTree> findPrivilegeTree() {
        return basePrivilegeExtendMapper.selectAll();
    }

	@Override
	public List<Privilege> findByUserId(long id) {
		List<Privilege> priviliges = basePrivilegeExtendMapper.findByUserId(id);
		return priviliges;
	}
    
}
