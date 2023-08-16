package com.example.Trello.services;

import com.example.Trello.model.entity.Board;
import com.example.Trello.model.dto.board.BoardCreation;

import java.util.List;

public interface BoardService {

    List<Board> getBoards();

    Board getBoardById(long id);

    void addBoard(BoardCreation boardCreation);

    void deleteBoard(long id);

    void updateBoard(long id, BoardCreation boardCreation);
}
