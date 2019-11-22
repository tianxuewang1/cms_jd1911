package com.briup.apps.cms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;
import com.briup.apps.cms.dao.ArticleMapper;
import com.briup.apps.cms.dao.extend.ArticleExtendMapper;
import com.briup.apps.cms.utils.CustomerException;
@Service
public class ArticleServiceImpl implements IArticleService{

	@Autowired
	private ArticleExtendMapper articleExtendmapper;
	@Autowired
	private ArticleMapper articlemapper;
	
	@Override
	public List<ArticleExtend> findAll() {
		List<ArticleExtend> list = articleExtendmapper.findAllcas();
		return list;
	}

	@Override
	public void saveAndUpdate(Article article) throws CustomerException{
		if(article.getId()!=null) {
				articlemapper.updateByPrimaryKey(article);
			}
			
		else {
			article.setPublishTime(2L);
            article.setStatus(ArticleExtend.STATUS_UNCHECK);
            article.setThumbUp("10");
            article.setThumbDown("20");
            articlemapper.insert(article);
		}
		
		
	}

	@Override
	public Article findarticleByid(Long id) {
		
		Article article = articlemapper.selectByPrimaryKey(id);
			return article;
			
	}

	

}
