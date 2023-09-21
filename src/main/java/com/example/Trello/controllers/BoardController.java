package com.example.Trello.controllers;

import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.services.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public List<BoardEntity> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("{id}")
    public BoardEntity getBoardById(@PathVariable final long id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public BoardEntity addBoard(@Valid @RequestBody final BoardCreation boardCreation) {
        return boardService.addBoard(boardCreation);
    }

    @DeleteMapping("{id}")
    public void deleteBoard(@PathVariable final long id) {
        boardService.deleteBoard(id);
    }

    @PutMapping("{id}")
    public BoardEntity updateBoard(@Valid @PathVariable final long id, @RequestBody final BoardCreation boardCreation) {
        return boardService.updateBoard(id, boardCreation);
    }
}
