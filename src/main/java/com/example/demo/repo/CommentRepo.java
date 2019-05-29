package com.example.demo.repo;

import com.example.demo.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommentRepo extends JpaRepository<Comment,Long> {

    Set<Comment> findDistinctByUserUserIdAndParentIsNull(Long id);

    Set<Comment> findDistinctByProductProductIdAndParentIsNull(String id);

}
