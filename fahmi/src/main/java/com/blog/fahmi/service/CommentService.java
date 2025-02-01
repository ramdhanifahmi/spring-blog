package com.blog.fahmi.service;

import com.blog.fahmi.domain.Comment;
import com.blog.fahmi.repository.CommentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    // Create: Saves a new comment
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }
        
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + id));
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    

    public void deleteCommentById(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("Comment not found with ID: " + id);
        }
        commentRepository.deleteById(id);
    }
}
