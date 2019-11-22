package com.briup.apps.cms.dao.extend;

import java.util.List;

import com.briup.apps.cms.bean.Article;
import com.briup.apps.cms.bean.extend.ArticleExtend;

public interface ArticleExtendMapper {
   List<ArticleExtend> findAllcas();
   
   ArticleExtend findarticlebyid(Long id);
   
   void saveAndUpdate(Article article);
}
