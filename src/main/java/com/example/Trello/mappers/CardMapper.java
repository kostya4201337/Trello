package com.example.Trello.mappers;

import com.example.Trello.model.dto.board.Board;
import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.dto.card.Card;
import com.example.Trello.model.dto.card.CardCreation;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {
    public Card map(final CardCreation cardCreation) {
        return new Card(cardCreation.getName(), cardCreation.getDescription());
    }
}
