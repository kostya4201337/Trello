package com.example.Trello.services.impl;

import com.example.Trello.mappers.BoardMapper;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.repositories.BoardRepository;
import com.example.Trello.services.exception.NoBoardFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {
    @InjectMocks
    private BoardServiceImpl boardService;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BoardMapper boardMapper;

    private final BoardEntity BOARD_ENTITY = new BoardEntity("a", "b");

    private final BoardCreation BOARD_CREATION = new BoardCreation("a", "b");

    private final String GET_BOARD_BY_ID_ERROR = "Board with given id doesn't exist";

    @Test
    void should_getBoardsOrderedByName() {
        //given
        given(boardRepository.findBoardEntitiesByOrderByName())
                .willReturn(List.of(BOARD_ENTITY));

        //when
        List<BoardEntity> boardEntities = boardService.getBoards();

        //then
        List<BoardEntity> expectedBoardEntities = List.of(BOARD_ENTITY);
        assertThat(boardEntities).containsAll(expectedBoardEntities);
    }

    @Test
    void should_getBoardById_whenBoardExists() {
        //given
        given(boardRepository.findById(1L)).willReturn(Optional.of(BOARD_ENTITY));

        //when
        BoardEntity boardEntity = boardService.getBoardById(1);

        //then
        assertThat(boardEntity).isEqualTo(BOARD_ENTITY);
    }

    @Test
    void should_throwException_whenBoardByIdDoesNotExist() {
        //given
        given(boardRepository.findById(1L)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> boardService.getBoardById(1));

        //then
        assertThat(throwable)
                .isInstanceOf(NoBoardFoundException.class)
                .hasMessageContaining(GET_BOARD_BY_ID_ERROR);
    }

    @Test
    void should_addBoard_when_boardPassed() {
        //given
        given(boardMapper.map(BOARD_CREATION)).willReturn(BOARD_ENTITY);

        //when
        boardService.addBoard(BOARD_CREATION);

        //then
        then(boardRepository).should().save(BOARD_ENTITY);
    }

    @Test
    void should_updateBoard_when_boardExists() {
        //given
        given(boardRepository.findById(1L)).willReturn(Optional.of(BOARD_ENTITY));

        //when
        boardService.updateBoard(1, BOARD_CREATION);

        //then
        then(boardRepository).should().save(BOARD_ENTITY);
    }

    @Test
    void should_throwException_when_updateBoardDoesNotExist() {
        //given
        given(boardRepository.findById(1L)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> boardService.updateBoard(1, BOARD_CREATION));

        //then
        assertThat(throwable)
                .isInstanceOf(NoBoardFoundException.class)
                .hasMessageContaining(GET_BOARD_BY_ID_ERROR);
    }

    @Test
    void should_deleteBoard_when_boardExists() {
        //given
        given(boardRepository.existsById(1L)).willReturn(true);

        //when
        boardService.deleteBoard(1);

        //then
        then(boardRepository).should().deleteById(1L);
    }

    @Test
    void should_throwException_when_deleteBoardDoesNotExist() {
        //given
        given(boardRepository.existsById(1L)).willReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> boardService.deleteBoard(1));

        //then
        assertThat(throwable)
                .isInstanceOf(NoBoardFoundException.class)
                .hasMessageContaining(GET_BOARD_BY_ID_ERROR);
    }
}