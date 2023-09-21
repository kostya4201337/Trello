package com.example.Trello.services;

import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.dto.board.BoardCreation;

import java.util.List;

public interface BoardService {

    List<BoardEntity> getBoards();

    BoardEntity getBoardById(long id);

    BoardEntity addBoard(BoardCreation boardCreation);

    void deleteBoard(long id);

    BoardEntity updateBoard(long id, BoardCreation boardCreation);
}
