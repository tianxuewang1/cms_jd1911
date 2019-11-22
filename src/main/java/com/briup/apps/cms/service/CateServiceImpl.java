package com.briup.apps.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.bean.CategoryExample;
import com.briup.apps.cms.dao.CategoryMapper;
import com.briup.apps.cms.utils.CustomerException;

@Service
public class CateServiceImpl implements IcategoryService{
	
	@Autowired
	 private CategoryMapper categorymapper;
	@Override
	public List<Category> finAll() {
		CategoryExample example=new CategoryExample();
		List<Category> list = categorymapper.selectByExample(example);
		return list;
	}

	@Override
	public void saveOrUpdate(Category category) throws CustomerException {
		if(category.getId()!=null) {
			categorymapper.updateByPrimaryKey(category);
		}
		else {
			//判断是否重名
			CategoryExample categoryExample = new CategoryExample();
			categoryExample.createCriteria().andNameEqualTo(category.getName());
			List<Category> list = categorymapper.selectByExample(categoryExample);
			if(list!=null) {
				throw new CustomerException("该栏目已经存在");
			}
			categorymapper.insert(category);
		}
	}

	@Override
	public void deleteById(Long id) throws CustomerException {
		Category category = categorymapper.selectByPrimaryKey(id);
		if(category==null){
			throw new CustomerException("该栏目不存在");
		}
		categorymapper.deleteByPrimaryKey(id);
	}

	@Override
	public void batchDelete(Long[] ids) throws CustomerException {
		
		for(Long id:ids) {
			this.deleteById(id);
		}
	}

	

}
