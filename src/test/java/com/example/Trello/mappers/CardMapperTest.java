package com.example.Trello.mappers;

import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.entity.CardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CardMapperTest {

    @InjectMocks
    CardMapper cardMapper;

    @Test
    void should_mapCardEntity_when_cardCreationPassed() {
        //given
        CardCreation cardCreation = new CardCreation("a", "b");

        //when
        CardEntity cardEntity = cardMapper.map(cardCreation);

        //then
        CardEntity expectedCardEntity = new CardEntity("a", "b");

        assertThat(cardEntity).isEqualTo(expectedCardEntity);
    }
}