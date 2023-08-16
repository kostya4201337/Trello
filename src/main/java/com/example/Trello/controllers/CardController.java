package com.example.Trello.controllers;

import com.example.Trello.model.dto.card.CardCreation;
import com.example.Trello.model.entity.CardEntity;
import com.example.Trello.services.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards/{board_id}/cards")
public class CardController {

    private static final String CARD_ADDED_MSG = "Card has been added";

    private static final String CARD_UPDATED_MSG = "Card has been updated";

    private static final String CARD_DELETED_MSG  = "Card has been deleted";

    private final CardService cardService;

    public CardController(final CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public String addCard(@PathVariable final long board_id, @RequestBody final CardCreation cardCreation){
        cardService.addCard(board_id, cardCreation);
        return CARD_ADDED_MSG;
    }

    @GetMapping
    public List<CardEntity> getCards(@PathVariable final long board_id) {
        return cardService.getCards(board_id);
    }

    @GetMapping("{id}")
    public CardEntity getCardById(@PathVariable final long id) {
        return cardService.getCardById(id);
    }

    @DeleteMapping("{id}")
    public String deleteCard(@PathVariable final long id) {
        cardService.deleteCard(id);
        return CARD_DELETED_MSG;
    }

    @PutMapping("{id}")
    public String updateCard(@PathVariable final long id, @RequestBody final CardCreation cardCreation) {
        cardService.updateCard(id, cardCreation);
        return CARD_UPDATED_MSG;
    }
}
