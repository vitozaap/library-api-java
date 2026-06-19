package com.library.api.controller;

import com.library.api.model.Comment;
import com.library.api.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/book/{bookId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@PathVariable String bookId, @RequestBody Comment comment) {
        return service.createComment(bookId, comment);
    }

    @GetMapping("/book/{bookId}")
    public List<Comment> getByBook(@PathVariable String bookId) {
        return service.getCommentsByBook(bookId);
    }


    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String commentId) {
        service.deleteComment(commentId);
    }
}