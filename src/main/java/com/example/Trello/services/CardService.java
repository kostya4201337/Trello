package com.example.Trello.services;

import com.example.Trello.model.entity.Card;
import com.example.Trello.model.dto.card.CardCreation;

import java.util.List;

public interface CardService {
    void addCard(long id, CardCreation cardCreation);

    List<Card> getCards(long id);

    Card getCardById(long id);

    void deleteCard(long id);

    void updateCard(long id, CardCreation cardCreation);
}
