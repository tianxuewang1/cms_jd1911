package com.briup.apps.cms.web.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Category;
import com.briup.apps.cms.service.IcategoryService;
import com.briup.apps.cms.utils.CustomerException;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private IcategoryService icategoryservice;
	
	@ApiOperation(value="查找所有文章")
	@GetMapping("/findall")
	public Message finAll(){
		List<Category> list = icategoryservice.finAll();
		Message message = MessageUtil.success("查找成功", list);
		return message;
	}
	
	
	@ApiOperation(value="更新或插入栏目")
	@PostMapping("/saveOrupdate")
	@ApiImplicitParams({@ApiImplicitParam(name ="id",value="主键", required =false,paramType = "query"),
		@ApiImplicitParam(name = "name", value="栏目名",required =true,paramType = "query"),
		@ApiImplicitParam(name = "no", value="序号",required =false,paramType = "query"),
		@ApiImplicitParam(name = "parentId", value="父栏目id",required =false,paramType = "query"),
		@ApiImplicitParam(name = "description", value="描述",required =true,paramType = "query"),
}
)
	 public Message saveOrUpdate(Long id , String name,Long no,Long parentId, String description) throws CustomerException{
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setNo(no);
		category.setParentId(parentId);
		category.setDescription(description);
		 icategoryservice.saveOrUpdate(category);
		 Message message = MessageUtil.success("更新成功");
		 return message;
		 
	 }
	
	@ApiOperation(value="删除栏目通过id")
	@GetMapping("/deletebyid")
	  public Message deleteById(Long id)throws CustomerException{
		  icategoryservice.deleteById(id);
		  Message message = MessageUtil.success("删除成功");
		  return message;
	  }
	
	@ApiOperation(value="批量删除栏目id")
	@PostMapping("/batchdelete")
	 Message batchDelete(Long[] ids)throws CustomerException{
		 icategoryservice.batchDelete(ids);
		 Message message = MessageUtil.success("删除成功");
		  return message;
	 }
	 
	 
}
