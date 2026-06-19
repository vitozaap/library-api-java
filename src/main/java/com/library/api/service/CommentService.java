package com.library.api.service;


import com.library.api.dto.CommentResponse;
import com.library.api.model.Book;
import com.library.api.model.Comment;
import com.library.api.repository.BookRepository;
import com.library.api.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentService {


    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentService(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    public CommentResponse createComment(String bookId, Comment newComment) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Id not found: " + bookId));

        newComment.setBook(book);


        return CommentResponse.fromEntity(commentRepository.save(newComment));
    }

    public List<CommentResponse> getCommentsByBook(String bookId) {
        return commentRepository.findByBookId(bookId)
                .stream()
                .map(CommentResponse::fromEntity)
                .toList();
    }

    public CommentResponse updateComment(String commentId, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Id not found: " + commentId));

        existingComment.setComment(updatedComment.getComment());

        return CommentResponse.fromEntity(commentRepository.save(existingComment));
    }

    public void deleteComment(String commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Id not found: " + commentId));

        commentRepository.delete(existingComment);
    }

}
