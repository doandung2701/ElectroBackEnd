package com.example.demo.service;

import com.example.demo.model.Comment;

import java.util.Set;

public interface CommentService {

    Set<Comment> findAll();

    Set<Comment> findDistinctByUserUserIdAndParentIsNull(Long id);

    Set<Comment> findDistinctByProductProductIdAndParentIsNull(String id);

    Comment findById(Long id);

    Comment save(Comment comment);
}
