package com.example.Trello.services.impl;

import com.example.Trello.mappers.BoardMapper;
import com.example.Trello.model.entity.Board;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.repositories.BoardRepository;
import com.example.Trello.services.BoardService;
import com.example.Trello.services.exception.NoBoardFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private static final String GET_BOARD_BY_ID_ERROR = "Board with given id doesn't exist";

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    public BoardServiceImpl(final BoardRepository boardRepository, final BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoardById(final long id) {
        final Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isEmpty()) {
            log.error(GET_BOARD_BY_ID_ERROR);
            throw new NoBoardFoundException(GET_BOARD_BY_ID_ERROR);
        }

        return boardOptional.get();
    }

    @Override
    public void addBoard(final BoardCreation boardCreation) {
        final Board board = boardMapper.map(boardCreation);
        boardRepository.save(board);
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
        final Optional<Board> boardOptional = boardRepository.findById(id);
        if (boardOptional.isEmpty()) {
            log.error(GET_BOARD_BY_ID_ERROR);
            throw new NoBoardFoundException(GET_BOARD_BY_ID_ERROR);
        }

        final Board board = boardOptional.get();
        board.setName(boardCreation.getName());
        board.setDescription(boardCreation.getDescription());
        boardRepository.save(board);
    }
}
