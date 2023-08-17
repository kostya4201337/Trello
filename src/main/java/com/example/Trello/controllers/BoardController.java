package com.example.Trello.controllers;

import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.services.BoardService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private static final String BOARD_ADDED_MSG = "Board has been added";

    private static final String BOARD_UPDATED_MSG = "Board has been updated";

    private static final String BOARD_DELETED_MSG  = "Board has been deleted";

    private final BoardService boardService;

    public BoardController(final BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public List<BoardEntity> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("{id}")
    public BoardEntity getBoardById(@PathVariable final long id) {
        return boardService.getBoardById(id);
    }

    @PostMapping
    public String addBoard(@Valid @RequestBody final BoardCreation boardCreation) {
        boardService.addBoard(boardCreation);
        return BOARD_ADDED_MSG;
    }

    @DeleteMapping("{id}")
    public String deleteBoard(@PathVariable final long id) {
        boardService.deleteBoard(id);
        return BOARD_DELETED_MSG;
    }

    @PutMapping("{id}")
    public String updateBoard(@Valid @PathVariable final long id, @RequestBody final BoardCreation boardCreation) {
        boardService.updateBoard(id, boardCreation);
        return BOARD_UPDATED_MSG;
    }
}
