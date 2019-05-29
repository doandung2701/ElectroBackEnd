package com.example.demo.service;

import com.example.demo.model.Comment;
import com.example.demo.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Repository
@Transactional
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Set<Comment> findAll() {
        return new HashSet<>(commentRepo.findAll());
    }

    @Override
    public Set<Comment> findDistinctByUserUserIdAndParentIsNull(Long id) {
        return commentRepo.findDistinctByUserUserIdAndParentIsNull(id);
    }

    @Override
    public Set<Comment> findDistinctByProductProductIdAndParentIsNull(String id) {
        return commentRepo.findDistinctByProductProductIdAndParentIsNull(id);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepo.findById(id).get();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepo.save(comment);
    }
}
