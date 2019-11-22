package com.briup.apps.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.utils.CustomerException;
@Service
public interface IcategoryService {
		
	
	List<Category> finAll();
	void saveOrUpdate(Category category) throws CustomerException;
	void deleteById(Long id)throws CustomerException;
	void batchDelete(Long[] ids)throws CustomerException;
	
}
