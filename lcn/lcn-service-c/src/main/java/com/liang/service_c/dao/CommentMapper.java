package com.liang.service_c.dao;

import com.liang.service_c.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    int addComment(Comment comment);
}
