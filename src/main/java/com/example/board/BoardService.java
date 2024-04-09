package com.example.board;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    private static final String[] basicBoardNames = {
            "자유 게시판",
            "개발 게시판",
            "일상 게시판",
            "사건사고 게시판"
    };

    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
        for(String boardName : basicBoardNames) {
            if (!this.boardRepository.existsByName(boardName)) {
                Board board = new Board();
                board.setName(boardName);
                this.boardRepository.save(board);
            }
        }
    }

    public List<BoardDto> readAll(){
        List<BoardDto> boards = new ArrayList<>();
        for (Board board: boardRepository.findAll())
            boards.add(BoardDto.fromEntity(board));
        return boards;
    }

    public BoardDto read(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow();
        return BoardDto.fromEntity(board);
    }
}
