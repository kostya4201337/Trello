package com.example.Trello.controllers;

import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.entity.CardEntity;
import com.example.Trello.services.impl.BoardServiceImpl;
import com.example.Trello.services.impl.CardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CardController.class)
class CardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private CardServiceImpl cardService;

    private final CardEntity CARD_ENTITY = CardEntity.builder().name("a").description("b").build();

    private final CardCreation CARD_CREATION = new CardCreation("a", "b");

    @Test
    void should_returnCards() throws Exception {

        //given
        List<CardEntity> list = List.of(CARD_ENTITY, CardEntity.builder().name("b").description("b").build());
        given(cardService.getCards(1)).willReturn(list);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/boards/1/cards"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(list);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnCardById() throws Exception {

        //given
        given(cardService.getCardById(1)).willReturn(CARD_ENTITY);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders.get("/boards/1/cards/1"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(CARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnCard_when_cardAdded() throws Exception {

        //given
        given(cardService.addCard(1, CARD_CREATION)).willReturn(CARD_ENTITY);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/boards/1/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CARD_CREATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(CARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnError_when_addCardInputInvalid() throws Exception {

        //given
        given(cardService.addCard(1, new CardCreation("", ""))).willReturn(CardEntity.builder().name("").description("").build());

        //when
        int status = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/boards/1/cards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CardCreation("", ""))))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }

    @Test
    void should_returnCard_when_cardUpdated() throws Exception {

        //given
        given(cardService.updateCard(1, CARD_CREATION)).willReturn(CARD_ENTITY);

        //when
        String responseBody = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/boards/1/cards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(CARD_CREATION)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        //then
        String expectedResponseBody = mapper.writeValueAsString(CARD_ENTITY);
        assertThat(responseBody).isEqualTo(expectedResponseBody);
    }

    @Test
    void should_returnError_when_updateCardInputInvalid() throws Exception {

        //given
        given(cardService.updateCard(1, new CardCreation("", ""))).willReturn(CardEntity.builder().name("").description("").build());

        //when
        int status = mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/boards/1/cards/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CardCreation("", ""))))
                .andReturn()
                .getResponse()
                .getStatus();

        //then
        assertThat(status).isEqualTo(400);
    }
}
