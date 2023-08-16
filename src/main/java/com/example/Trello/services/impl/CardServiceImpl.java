package com.example.Trello.services.impl;

import com.example.Trello.mappers.CardMapper;
import com.example.Trello.model.entity.Card;
import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.repositories.CardRepository;
import com.example.Trello.services.BoardService;
import com.example.Trello.services.CardService;
import com.example.Trello.services.exception.NoCardFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    private static final String GET_CARD_BY_ID_ERROR = "Card with given id doesn't exist";

    private final CardMapper cardMapper;

    private final CardRepository cardRepository;

    private final BoardService boardService;

    public CardServiceImpl(final CardMapper cardMapper, final CardRepository cardRepository, final BoardService boardService) {
        this.cardMapper = cardMapper;
        this.cardRepository = cardRepository;
        this.boardService = boardService;
    }

    @Override
    public void addCard(final long id, final CardCreation cardCreation) {
        final Card card = cardMapper.map(cardCreation);
        card.setBoard(boardService.getBoardById(id));
        cardRepository.save(card);
    }

    @Override
    public List<Card> getCards(final long id) {
        return boardService.getBoardById(id).getCards();
    }

    @Override
    public Card getCardById(final long id) {
        final Optional<Card> optional = cardRepository.findById(id);
        if (optional.isEmpty()) {
            log.error(GET_CARD_BY_ID_ERROR);
            throw new NoCardFoundException(GET_CARD_BY_ID_ERROR);
        }

        return optional.get();
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
        final Optional<Card> optional = cardRepository.findById(id);
        if (optional.isEmpty()) {
            log.error(GET_CARD_BY_ID_ERROR);
            throw new NoCardFoundException(GET_CARD_BY_ID_ERROR);
        }

        final Card card = optional.get();
        card.setName(cardCreation.getName());
        card.setDescription(cardCreation.getDescription());

        cardRepository.save(card);
    }
}
