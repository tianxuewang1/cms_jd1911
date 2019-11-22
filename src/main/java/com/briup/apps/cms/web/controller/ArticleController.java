package com.briup.apps.cms.web.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.service.IArticleService;
import com.briup.apps.cms.utils.Message;
import com.briup.apps.cms.utils.MessageUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private IArticleService  iarticleservice;
	
	
	@ApiOperation(value="级联查询所有文章的controller")
	@GetMapping("/findallmessage")
	public Message findallcas3(){
		List<ArticleExtend> list = iarticleservice.findAll();
		Message message2 = MessageUtil.success("查询结果", list);
		
		return message2;
	}
	
	@ApiOperation(value="更新或删除文章")
	@ApiImplicitParams({@ApiImplicitParam(name="id",value="主键",paramType = "query"  ),
						@ApiImplicitParam(name="title",value="文章标题",paramType = "query",required = true ),
						@ApiImplicitParam(name="content",value="文章内容",paramType = "query",required = true ),
						@ApiImplicitParam(name="authorId",value="作者编号",paramType = "query" ),
						@ApiImplicitParam(name="categoryId",value="栏目编号",paramType = "query",required = true ),
						@ApiImplicitParam(name="source",value="来源",paramType = "query" )
	
	}
			)
	@PostMapping("/saveOrupdate")
	public Message saveAndupdate(Long id,@NotNull String title,@NotNull String content,String source,Long publishTime,Long readTimes,String status,String thumbUp,String thumbDown
			,Long authorId,@NotNull Long categoryId){
		Article article = new Article();
		article.setAuthorId(authorId);
		article.setCategoryId(categoryId);
		article.setContent(content);
		article.setId(categoryId);
		article.setPublishTime(publishTime);
		article.setReadTimes(readTimes);
		article.setSource(source);
		article.setStatus(status);
		article.setThumbDown(thumbDown);
		article.setTitle(title);
		article.setThumbUp(thumbUp);
		iarticleservice.saveAndUpdate(article);
		Message message = MessageUtil.success("更新成功");
		return message;
	}
	
	@ApiOperation(value="查询通过id")
	@ApiImplicitParams(@ApiImplicitParam(name="id",value="主键",paramType = "query" )
			)
	@GetMapping("/findalarticlebyid")
	public Message findallcas3byid(Long id){
		Article article = iarticleservice.findarticleByid(id);
		Message message = MessageUtil.success(article);
		return message;
	}
	
	
}
