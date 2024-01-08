package com.example.board.dto;

import com.example.board.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@Getter
@ToString
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    private String password;

    public ArticleDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleDto(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }

    // static factory method
    public static ArticleDto fromEntity(Article entity) {
        ArticleDto dto = new ArticleDto();
        dto.id = entity.getId();
        dto.title = entity.getTitle();
        dto.content = entity.getContent().replace("\n", "<br>");
        dto.password = entity.getPassword();
       /* dto.comments = new ArrayList<>();
        for (Comment comment: entity.getComments())
            dto.comments.add(CommentDto.fromEntity(comment));*/
        return dto;
    }

}
