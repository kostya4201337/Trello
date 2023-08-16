package com.example.Trello.services;

import com.example.Trello.model.entity.CardEntity;
import com.example.Trello.model.dto.card.CardCreation;

import java.util.List;

public interface CardService {
    void addCard(long id, CardCreation cardCreation);

    List<CardEntity> getCards(long id);

    CardEntity getCardById(long id);

    void deleteCard(long id);

    void updateCard(long id, CardCreation cardCreation);
}
