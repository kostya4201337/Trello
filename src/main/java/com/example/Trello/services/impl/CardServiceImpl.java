package com.example.Trello.services.impl;

import com.example.Trello.mappers.CardMapper;
import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.entity.CardEntity;
import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.repositories.CardRepository;
import com.example.Trello.services.BoardService;
import com.example.Trello.services.CardService;
import com.example.Trello.services.exception.NoCardFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private static final String GET_CARD_BY_ID_ERROR = "Card with given id doesn't exist";

    private final CardMapper cardMapper;

    private final CardRepository cardRepository;

    @Override
    public void addCard(final long boardId, final CardCreation cardCreation) {
        final CardEntity cardEntity = cardMapper.map(cardCreation);
        cardEntity.setBoardId(boardId);
        cardRepository.save(cardEntity);
    }

    @Override
    public List<CardEntity> getCards(final long id) {
        return cardRepository.findCardEntitiesByBoardIdOrderByCreatedAtDesc(id);
    }

    @Override
    public CardEntity getCardById(final long id) {
        final Optional<CardEntity> cardOptional = cardRepository.findById(id);
        if (cardOptional.isEmpty()) {
            log.error(GET_CARD_BY_ID_ERROR);
            throw new NoCardFoundException(GET_CARD_BY_ID_ERROR);
        }

        return cardOptional.get();
    }

    @Override
    public void deleteCard(final long id) {
        if (!cardRepository.existsById(id)) {
            log.error(GET_CARD_BY_ID_ERROR);
            throw new NoCardFoundException(GET_CARD_BY_ID_ERROR);
        }

        cardRepository.deleteById(id);
    }

    @Override
    public void updateCard(final long id, final CardCreation cardCreation) {
        final Optional<CardEntity> cardOptional = cardRepository.findById(id);
        if (cardOptional.isEmpty()) {
            log.error(GET_CARD_BY_ID_ERROR);
            throw new NoCardFoundException(GET_CARD_BY_ID_ERROR);
        }

        final CardEntity cardEntity = cardOptional.get();
        cardEntity.setName(cardCreation.getName());
        cardEntity.setDescription(cardCreation.getDescription());
        cardRepository.save(cardEntity);
    }
}
