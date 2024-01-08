package com.example.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Board {
    // 게시판 객체
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
