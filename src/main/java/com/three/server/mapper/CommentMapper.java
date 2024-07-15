package com.three.server.mapper;
import com.three.server.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insert(Comment comment);
    int deleteById(Integer id);
    int update(Comment comment);
    List<Comment> selectAll();
    List<Comment> selectByGoodId(Integer good);
    List<Comment> selectByCustomerId(Integer customer);
}
