package com.example.Trello.controllers;

import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.services.BoardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
class BoardControllerTest {

    @InjectMocks
    private BoardController boardController;

    @Mock
    private BoardService boardService;

    private final BoardEntity BOARD_ENTITY = BoardEntity.builder().name("a").description("b").build();

    private final BoardCreation BOARD_CREATION = new BoardCreation("a", "b");

    @Test
    void should_getBoards() {
        //given
        List<BoardEntity> expectedBoards = List.of(BOARD_ENTITY);

        given(boardService.getBoards()).willReturn(expectedBoards);

        //when
        List<BoardEntity> boardEntities = boardController.getBoards();

        //then
        assertThat(boardEntities).containsAll(expectedBoards);
    }

    @Test
    void should_getBoardById() {
        //given
        given(boardService.getBoardById(1L)).willReturn(BOARD_ENTITY);

        //when
        BoardEntity boardEntity = boardController.getBoardById(1);

        //then
        assertThat(boardEntity).isEqualTo(BOARD_ENTITY);
    }

    @Test
    void should_returnBoard_when_boardAdded() {
        //given
        given(boardService.addBoard(BOARD_CREATION)).willReturn(BOARD_ENTITY);

        //when
        BoardEntity boardEntity = boardController.addBoard(BOARD_CREATION);

        //then
        assertThat(boardEntity).isEqualTo(BOARD_ENTITY);
    }

    @Test
    void should_returnBoard_when_boardUpdated() {
        //given
        given(boardService.updateBoard(1, BOARD_CREATION)).willReturn(BOARD_ENTITY);

        //when
        BoardEntity boardEntity = boardController.updateBoard(1, BOARD_CREATION);

        //then
        assertThat(boardEntity).isEqualTo(BOARD_ENTITY);
    }
}