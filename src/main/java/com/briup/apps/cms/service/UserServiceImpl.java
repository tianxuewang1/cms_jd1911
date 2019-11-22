package com.briup.apps.cms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.User_role;
import com.briup.apps.cms.bean.User_roleExample;
import com.briup.apps.cms.bean.user;
import com.briup.apps.cms.bean.userExample;
import com.briup.apps.cms.bean.extend.UserExtend;
import com.briup.apps.cms.dao.User_roleMapper;
import com.briup.apps.cms.dao.userMapper;
import com.briup.apps.cms.dao.extend.UserExtendMapper;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.vmuser.UserRoleVM;
import com.briup.apps.cms.vmuser.Uservm;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private userMapper usermapper;
	@Autowired
	private UserExtendMapper userExtendMapper;
	@Autowired
	private User_roleMapper userrolemapper;
	@Autowired
	private userExample example;

	// 查找所有用户
	@Override
	public List<user> findAll() {

		List<user> users = usermapper.selectByExample(example);

		return users;
	}

	// 查找所有用户，级联找出用户角色
	@Override
	public List<UserExtend> cascadeRoleFindAll() {
		List<UserExtend> userExtends = userExtendMapper.selectAll();
		if (userExtends.equals(null)) {
			throw new CustomerException("无数据");
		} else {

			return userExtends;
		}
	}

	// 保存用户，名字不能重复
	@Override
	public void saveOrUpdate(user baseUser) throws CustomerException {
		// 传过来的是整个对象
		if (baseUser.getId() != null) {
			usermapper.updateByPrimaryKey(baseUser);
		} else {
			example.clear();
			example.createCriteria().andUsernameEqualTo(baseUser.getUsername());
			List<user> users = usermapper.selectByExample(example);
			if (!(users.equals(null)) && (users.size() != 0)) {
				throw new CustomerException("用户名不能重复");
			}
			usermapper.insert(baseUser);
		}
	}

	// 通过id删除用户
	@Override
	public void deleteById(Long id) {
		if (id == null) {

			throw new CustomerException("请选择对应的用户");
		}
		usermapper.deleteByPrimaryKey(id);

	}

	// 将用户id和角色id绑定
	@Override
	public void setRoles(UserRoleVM userRole) {
		
		User_roleExample role_userexample=new User_roleExample();
		role_userexample.createCriteria().andUserIdEqualTo(userRole.getId());
		List<User_role> user_roles = userrolemapper.selectByExample(role_userexample);
		List<Long> oldRoles = new ArrayList<>();
		// 用户角色关系,获取所有老的角色
		Iterator<User_role> iterator = user_roles.iterator();
		while(iterator.hasNext()) {
			Long roleId = iterator.next().getRoleId();
			oldRoles.add(roleId);
		}
		// [1,2,3] -> [3,4] 添加1,2 => [1,2,3,4]
        // 依次判断新角色是否存在于list中，如果不存在则添加
		for(Long roleId : userRole.getRoles()){
            if(!oldRoles.contains(roleId)){
                User_role userRole2 = new User_role();
                userRole2.setRoleId(roleId);
                userRole2.setUserId(userRole.getId());
                userrolemapper.insert(userRole2);
            }
        }
		// [1,2,3] -> [1,2,3,4]   删除 3,4  => [1,2]
        // 依次判断老的角色是否存在于roles中，如果不存在则删除此角色id的记录
		 for(User_role userRole3 : user_roles){
	            if(!userRole.getRoles().contains(userRole3.getRoleId())){
	            	User_roleExample example1 = new User_roleExample();
	            	example1.createCriteria().andRoleIdEqualTo(userRole3.getRoleId()).andUserIdEqualTo(userRole.getId());
	            	userrolemapper.deleteByExample(example1);
	            }
	        }
		
		
	}

	@Override
	public user login(Uservm userVM) throws CustomerException {
		 userExample example = new userExample();
	        example.createCriteria().andUsernameEqualTo(userVM.getUsername());
	        List<user> list = usermapper.selectByExample(example);
	        if(list.size()<=0){
	            throw new CustomerException("该用户不存在");
	        }
	        user user = list.get(0);
	        if(!user.getPassword().equals(userVM.getPassword())){
	            throw new CustomerException("密码不匹配");
	        }
	        return user;
		
	}

}
