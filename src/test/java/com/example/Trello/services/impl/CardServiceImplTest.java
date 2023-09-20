package com.example.Trello.services.impl;

import com.example.Trello.mappers.CardMapper;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.entity.CardEntity;
import com.example.Trello.repositories.CardRepository;
import com.example.Trello.services.exception.NoCardFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {
    @InjectMocks
    private CardServiceImpl cardService;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardMapper cardMapper;

    private final CardEntity CARD_ENTITY = CardEntity.builder().name("a").description("b").build();

    private final CardCreation CARD_CREATION = new CardCreation("a", "b");

    private final String GET_CARD_BY_ID_ERROR = "Card with given id doesn't exist";

    @Test
    void should_getCardsOrderedByName() {
        //given
        given(cardRepository.findCardEntitiesByBoardIdOrderByCreatedAtDesc(1))
                .willReturn(List.of(CARD_ENTITY));

        //when
        List<CardEntity> cardEntities = cardService.getCards(1);

        //then
        List<CardEntity> expectedCardEntities = List.of(CARD_ENTITY);
        assertThat(cardEntities).containsAll(expectedCardEntities);
    }

    @Test
    void should_getCardById_whenCardExists() {
        //given
        given(cardRepository.findById(1L)).willReturn(Optional.of(CARD_ENTITY));

        //when
        CardEntity cardEntity = cardService.getCardById(1);

        //then
        assertThat(cardEntity).isEqualTo(CARD_ENTITY);
    }

    @Test
    void should_throwException_whenCardByIdDoesNotExist() {
        //given
        given(cardRepository.findById(1L)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> cardService.getCardById(1));

        //then
        assertThat(throwable)
                .isInstanceOf(NoCardFoundException.class)
                .hasMessageContaining(GET_CARD_BY_ID_ERROR);
    }

    @Test
    void should_addCard_when_cardPassed() {
        //given
        given(cardMapper.map(CARD_CREATION)).willReturn(CARD_ENTITY);

        //when
        cardService.addCard(1, CARD_CREATION);

        //then
        CardEntity expectedCardEntity = CardEntity.builder().name("a").description("b").build();;
        expectedCardEntity.setBoardId(1);
        then(cardRepository).should().save(expectedCardEntity);
    }

    @Test
    void should_updateCard_when_cardExists() {
        //given
        given(cardRepository.findById(1L)).willReturn(Optional.of(CARD_ENTITY));

        //when
        cardService.updateCard(1, CARD_CREATION);

        //then
        then(cardRepository).should().save(CARD_ENTITY);
    }

    @Test
    void should_throwException_when_updateCardDoesNotExist() {
        //given
        given(cardRepository.findById(1L)).willReturn(Optional.empty());

        //when
        Throwable throwable = catchThrowable(() -> cardService.updateCard(1, CARD_CREATION));

        //then
        assertThat(throwable)
                .isInstanceOf(NoCardFoundException.class)
                .hasMessageContaining(GET_CARD_BY_ID_ERROR);
    }

    @Test
    void should_deleteBoard_when_cardExists() {
        //given
        given(cardRepository.existsById(1L)).willReturn(true);

        //when
        cardService.deleteCard(1);

        //then
        then(cardRepository).should().deleteById(1L);
    }

    @Test
    void should_throwException_when_deleteCardDoesNotExist() {
        //given
        given(cardRepository.existsById(1L)).willReturn(false);

        //when
        Throwable throwable = catchThrowable(() -> cardService.deleteCard(1));

        //then
        assertThat(throwable)
                .isInstanceOf(NoCardFoundException.class)
                .hasMessageContaining(GET_CARD_BY_ID_ERROR);
    }
}