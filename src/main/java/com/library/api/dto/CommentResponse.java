package com.library.api.dto;

import com.library.api.model.Comment;

public record CommentResponse(String id, String comment, String bookId) {

    // Adicionei somente para nao gerar recursividade na hora de retornar os comentários, pois estava retornando o objeto book inteiro em vez de somente o ID. rs
    public static CommentResponse fromEntity(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getComment(),
                comment.getBook() != null ? comment.getBook().getId() : null // Se tiver livro, ele pega somente o id, evitando bugar na recursividade
        );
    }
}
