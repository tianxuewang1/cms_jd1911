package com.briup.apps.cms.dao.extend;

import com.briup.apps.cms.bean.Comment;
import com.briup.apps.cms.bean.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentExtendMapper {
    List<Comment> findcommentbyartcleid(Long id);
}