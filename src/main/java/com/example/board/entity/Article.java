package com.example.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/* 전체 클래스에는 Getter를 사용자가 할당할 수 있는 속성에는 Setter를 적용
 가변길이를 갖는 큰 데이터를 저장하기 위해 content에 @Lob을 적용*/
@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    @Lob
    private String content;

    @Setter
    private String password;

    public Article(){}

    public Article(String title, String content, String password) {
        this.title = title;
        this.content = content;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(password, article.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, password);
    }
}
