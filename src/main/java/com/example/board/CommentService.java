package com.example.board;

import com.example.board.dto.CommentDto;
import com.example.board.entity.Article;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.repo.ArticleRepository;
import com.example.board.repo.BoardRepository;
import com.example.board.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


    public CommentDto createComment(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow();

        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setContent(dto.getContent());
        comment.setPassword(dto.getPassword());
        return CommentDto.fromEntity(commentRepository.save(comment));
    }


    public void deleteComment(Long id, String password) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        if (comment.getPassword().equals(password)) {
            commentRepository.delete(comment);
        }
        commentRepository.delete(comment);
    }
}
