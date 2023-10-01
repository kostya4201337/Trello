package com.example.Trello.repositories;

import com.example.Trello.model.entity.BoardEntity;
import com.example.Trello.model.entity.CardEntity;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryIntegrationTest {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void clearDB() {
        entityManager.flush();
    }

    @Test
    void should_returnCardEntitiesByBoardIdOrderByCreatedAtDesc() {

        //given
        long id = entityManager.persist(BoardEntity.builder().build()).getId();
        CardEntity cardEntity1 = entityManager.persist(CardEntity.builder().boardId(id).name("a").build());
        CardEntity cardEntity2 = entityManager.persist(CardEntity.builder().boardId(id).name("b").build());
        CardEntity cardEntity3 = entityManager.persist(CardEntity.builder().boardId(id).name("c").build());

        //when
        List<CardEntity> cards = cardRepository.findCardEntitiesByBoardIdOrderByCreatedAtDesc(id);

        //then
        assertThat(cards).containsExactly(cardEntity3, cardEntity2, cardEntity1);
    }
}