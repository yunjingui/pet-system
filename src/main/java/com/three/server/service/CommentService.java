package com.three.server.service;

import com.three.server.entity.Comment;

import java.util.List;

public interface CommentService {
    Boolean addComment(Comment comment);
    Boolean deleteCommentById(Integer id);
    Boolean updateComment(Comment comment);
    List<Comment> findCommentByGood(Integer goodId);
    List<Comment> findCommentByCustomer(Integer customerId);
    List<Comment> findAllComments();

}
