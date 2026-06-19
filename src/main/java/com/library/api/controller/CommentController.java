package com.library.api.controller;

import com.library.api.dto.CommentResponse;
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
    public CommentResponse create(@PathVariable String bookId, @RequestBody Comment comment) {
        return service.createComment(bookId, comment);
    }

    @GetMapping("/book/{bookId}")
    public List<CommentResponse> getByBook(@PathVariable String bookId) {
        return service.getCommentsByBook(bookId);
    }

    @PutMapping("/book/{bookId}")
    public CommentResponse editComment(@PathVariable String bookId, @RequestBody Comment comment) {
        return service.updateComment(bookId, comment);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String commentId) {
        service.deleteComment(commentId);
    }
}