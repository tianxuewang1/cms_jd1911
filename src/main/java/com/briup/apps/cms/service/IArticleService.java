package com.briup.apps.cms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;

@Service
public interface IArticleService {

	List<ArticleExtend> findAll();
	
	void saveAndUpdate(Article article);
	
	Article findarticleByid(Long id);
}
