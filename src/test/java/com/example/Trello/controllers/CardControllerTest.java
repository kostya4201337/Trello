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
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CardControllerTest {

    @InjectMocks
    private CardController cardController;

    @Mock
    private CardService cardService;

    private final CardEntity CARD_ENTITY = CardEntity.builder().name("a").description("b").build();

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
    void should_returnCard_when_cardAdded() {
        //given
        given(cardService.addCard(1, CARD_CREATION)).willReturn(CARD_ENTITY);

        //when
        CardEntity cardEntity = cardController.addCard(1, CARD_CREATION);

        //then
        assertThat(cardEntity).isEqualTo(CARD_ENTITY);
    }

    @Test
    void should_returnCard_when_cardUpdated() {
        //given
        given(cardService.updateCard(1, CARD_CREATION)).willReturn(CARD_ENTITY);

        //when
        CardEntity cardEntity = cardController.updateCard(1, CARD_CREATION);

        //then
        assertThat(cardEntity).isEqualTo(CARD_ENTITY);
    }

    @Test
    void should_successfullyExecuteDeleteCard() {
        //when
        cardController.deleteCard(1);

        //then
        then(cardService).should().deleteCard(1);
    }
}