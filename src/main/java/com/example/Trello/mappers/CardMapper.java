package com.example.Trello.mappers;

import com.example.Trello.model.entity.CardEntity;
import com.example.Trello.model.dto.card.CardCreation;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public CardEntity map(final CardCreation cardCreation) {
        return CardEntity
                .builder()
                .name(cardCreation.name())
                .description(cardCreation.description())
                .build();
    }
}
