package com.three.server.service.impl;

import com.three.server.entity.Comment;
import com.three.server.mapper.CommentMapper;
import com.three.server.mapper.GoodMapper;
import com.three.server.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final GoodMapper goodMapper;

    @Override
    @Transactional
    public Boolean addComment(Comment comment) {
        if (commentMapper.insert(comment) > 0) {
            goodMapper.updateGoodLevel(comment.getGood());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteCommentById(Integer id) {
        if (commentMapper.deleteById(id) > 0) {
            goodMapper.updateGoodLevel(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateComment(Comment comment) {
        return commentMapper.update(comment) > 0;
    }

    @Override
    public List<Comment> findCommentByGood(Integer goodId) {
        return commentMapper.selectByGoodId(goodId);
    }

    @Override
    public List<Comment> findCommentByCustomer(Integer customerId) {
        return commentMapper.selectByCustomerId(customerId);
    }

    @Override
    public List<Comment> findAllComments() {
        return commentMapper.selectAll();
    }
}
