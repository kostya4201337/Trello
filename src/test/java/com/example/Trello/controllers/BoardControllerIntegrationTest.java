package com.example.Trello.controllers;

import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.services.BoardService;
import com.example.Trello.services.impl.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BoardController.class)
class BoardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BoardServiceImpl boardService;

    private final BoardEntity BOARD_ENTITY = BoardEntity.builder().name("a").description("b").build();

    private final BoardCreation BOARD_CREATION = new BoardCreation("a", "b");

    @Test
    void should_returnBoards() throws Exception {

        //given
        List<BoardEntity> list = List.of(BOARD_ENTITY, BoardEntity.builder().name("b").description("b").build());
        given(boardService.getBoards()).willReturn(list);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/boards"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(list);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnBoardById() throws Exception {

        //given
        given(boardService.getBoardById(1)).willReturn(BOARD_ENTITY);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/boards/1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(BOARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnBoard_when_boardAdded() throws Exception {

        //given
        given(boardService.addBoard(BOARD_CREATION)).willReturn(BOARD_ENTITY);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(BOARD_CREATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(BOARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnError_when_addBoardInputInvalid() throws Exception {

        //given
        given(boardService.addBoard(new BoardCreation("", ""))).willReturn(BoardEntity.builder().name("").description("").build());

        //when
        int status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new BoardCreation("", ""))))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }

    @Test
    void should_returnBoard_when_boardUpdated() throws Exception {

        //given
        given(boardService.updateBoard(1, BOARD_CREATION)).willReturn(BOARD_ENTITY);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/boards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(BOARD_CREATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(BOARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnError_when_updateBoardInputInvalid() throws Exception {

        //given
        given(boardService.updateBoard(1, new BoardCreation("", ""))).willReturn(BoardEntity.builder().name("").description("").build());

        //when
        int status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/boards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new BoardCreation("", ""))))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }
}
