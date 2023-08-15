package com.example.Trello.mappers;

import com.example.Trello.model.dto.board.Board;
import com.example.Trello.model.dto.board.BoardCreation;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {
    public Board map(final BoardCreation boardCreation) {
        return new Board(boardCreation.getName(), boardCreation.getDescription());
    }
}
