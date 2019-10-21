package com.liang.service_c.dao;

import com.liang.service_c.entity.CommentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentInfoMapper {

    int addCommentInfo(CommentInfo commentInfo);


}
