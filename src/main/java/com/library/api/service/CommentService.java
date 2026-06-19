package com.library.api.service;


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

    public Comment createComment(String bookId, Comment newComment) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Livro com o ID " + bookId + " não foi encontrado."));

        newComment.setBook(book);


        return commentRepository.save(newComment);
    }

    public List<Comment> getCommentsByBook(String bookId) {
        return commentRepository.findByBookId(bookId);
    }

    public Comment updateComment(String commentId, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentário com o ID " + commentId + " não encontrado."));

        existingComment.setComment(updatedComment.getComment());

        return commentRepository.save(existingComment);
    }

    public void deleteComment(String commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentário com o ID " + commentId + " não encontrado."));

        commentRepository.delete(existingComment);
    }

}
