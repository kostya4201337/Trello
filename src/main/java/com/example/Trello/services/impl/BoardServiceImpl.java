package com.example.Trello.services.impl;

import com.example.Trello.mappers.BoardMapper;
import com.example.Trello.model.dto.board.Board;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.repositories.BoardRepository;
import com.example.Trello.services.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final BoardMapper boardMapper;

    public BoardServiceImpl(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    @Override
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoardById(final long id) {
        Optional<Board> board = boardRepository.findById(id);

        if(board.isEmpty()) {
            throw new RuntimeException();
        }

        return board.get();
    }

    @Override
    public void addBoard(final BoardCreation boardCreation) {
        Board board = boardMapper.map(boardCreation);
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(final long id) {

        if (!boardRepository.existsById(id)) {
            throw new RuntimeException();
        }

        boardRepository.deleteById(id);
    }

    @Override
    public void updateBoard(final long id, final BoardCreation boardCreation) {
        Optional<Board> optional = boardRepository.findById(id);

        if(optional.isEmpty()){
            throw new RuntimeException();
        }

        Board board = optional.get();
        board.setName(boardCreation.getName());
        board.setDescription(boardCreation.getDescription());
        boardRepository.save(board);
    }
}
