package com.example.Trello.controllers;

import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.entity.CardEntity;
import com.example.Trello.services.BoardService;
import com.example.Trello.services.CardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @InjectMocks
    private CardController cardController;

    @Mock
    private CardService cardService;

    private final CardEntity CARD_ENTITY = new CardEntity("a", "b");

    private final CardCreation CARD_CREATION = new CardCreation("a", "b");

    @Test
    void should_getCards() {
        //given
        List<CardEntity> expectedCards = List.of(CARD_ENTITY);

        given(cardService.getCards(1)).willReturn(expectedCards);

        //when
        List<CardEntity> cardEntities = cardController.getCards(1);

        //then
        assertThat(cardEntities).containsAll(expectedCards);
    }

    @Test
    void should_getCardById() {
        //given
        given(cardService.getCardById(1L)).willReturn(CARD_ENTITY);

        //when
        CardEntity cardEntity = cardController.getCardById(1);

        //then
        assertThat(cardEntity).isEqualTo(CARD_ENTITY);
    }

    @Test
    void should_returnSuccessfulMessage_when_cardAdded() {
        //given
        willDoNothing().given(cardService).addCard(1, CARD_CREATION);

        //when
        String message = cardController.addCard(1, CARD_CREATION);

        //then
        String expectedMessage = "Card has been added";
        assertThat(message).isEqualTo(expectedMessage);
    }

    @Test
    void should_returnSuccessfulMessage_when_cardUpdated() {
        //given
        willDoNothing().given(cardService).updateCard(1, CARD_CREATION);

        //when
        String message = cardController.updateCard(1, CARD_CREATION);

        //then
        String expectedMessage = "Card has been updated";
        assertThat(message).isEqualTo(expectedMessage);
    }

    @Test
    void should_returnSuccessfulMessage_when_cardDeleted() {
        //given
        willDoNothing().given(cardService).deleteCard(1);

        //when
        String message = cardController.deleteCard(1);

        //then
        String expectedMessage = "Card has been deleted";
        assertThat(message).isEqualTo(expectedMessage);
    }
}