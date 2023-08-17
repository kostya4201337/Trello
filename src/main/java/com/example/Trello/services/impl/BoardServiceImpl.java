package com.example.Trello.services.impl;

import com.example.Trello.mappers.BoardMapper;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.repositories.BoardRepository;
import com.example.Trello.services.BoardService;
import com.example.Trello.services.exception.NoBoardFoundException;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private static final String GET_BOARD_BY_ID_ERROR = "Board with given id doesn't exist";

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    private final Flyway flyway;

    public BoardServiceImpl(final BoardRepository boardRepository, final BoardMapper boardMapper, final Flyway flyway) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
        this.flyway = flyway;
    }

    @Override
    public List<BoardEntity> getBoards() {
        return boardRepository.findBoardEntitiesByOrderByName();
    }

    @Override
    public BoardEntity getBoardById(final long id) {
        final Optional<BoardEntity> boardOptional = boardRepository.findById(id);
        if (boardOptional.isEmpty()) {
            log.error(GET_BOARD_BY_ID_ERROR);
            throw new NoBoardFoundException(GET_BOARD_BY_ID_ERROR);
        }

        return boardOptional.get();
    }

    @Override
    public void addBoard(final BoardCreation boardCreation) {
        final BoardEntity boardEntity = boardMapper.map(boardCreation);
        boardRepository.save(boardEntity);
    }

    @Override
    public void deleteBoard(final long id) {
        if (!boardRepository.existsById(id)) {
            log.error(GET_BOARD_BY_ID_ERROR);
            throw new NoBoardFoundException(GET_BOARD_BY_ID_ERROR);
        }
        boardRepository.deleteById(id);
    }

    @Override
    public void updateBoard(final long id, final BoardCreation boardCreation) {
        final Optional<BoardEntity> boardOptional = boardRepository.findById(id);
        if (boardOptional.isEmpty()) {
            log.error(GET_BOARD_BY_ID_ERROR);
            throw new NoBoardFoundException(GET_BOARD_BY_ID_ERROR);
        }

        final BoardEntity boardEntity = boardOptional.get();
        boardEntity.setName(boardCreation.getName());
        boardEntity.setDescription(boardCreation.getDescription());
        boardRepository.save(boardEntity);
    }
}
