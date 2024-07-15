package com.three.server.controller;


import com.three.server.common.responseBodyCustom.ApiResponse;
import com.three.server.entity.Comment;
import com.three.server.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/add")
    public ApiResponse<Void> addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment) ? ApiResponse.success() : ApiResponse.fail(500, "添加评论失败");
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Void> deleteComment(@PathVariable Integer id) {
        return commentService.deleteCommentById(id) ? ApiResponse.success() : ApiResponse.fail(500, "删除评论失败");
    }

    @PutMapping("/update")
    public ApiResponse<Void> updateComment(@RequestBody Comment comment) {
        return commentService.updateComment(comment) ? ApiResponse.success() : ApiResponse.fail(500, "更新评论失败");
    }

    @GetMapping("/findByGood/{goodId}")
    public ApiResponse<List<Comment>> findCommentByGood(@PathVariable Integer goodId) {
        return ApiResponse.success(commentService.findCommentByGood(goodId));
    }

    @GetMapping("/findByCustomer/{customerId}")
    public ApiResponse<List<Comment>> findCommentByCustomer(@PathVariable Integer customerId) {
        return ApiResponse.success(commentService.findCommentByCustomer(customerId));
    }

    @GetMapping("/findAllComments")
    public ApiResponse<List<Comment>> findAllComments() {
        return ApiResponse.success(commentService.findAllComments());
    }

}
