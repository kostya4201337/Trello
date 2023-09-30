package com.example.Trello.repositories;

import com.example.Trello.model.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoardRepositoryIntegrationTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void clearDB() {
        entityManager.flush();
    }

    @Order(1)
    @Test
    void should_returnOrderedByName() {

        //given
        BoardEntity boardEntity1 = entityManager.persist(BoardEntity.builder().name("b").build());
        BoardEntity boardEntity2 = entityManager.persist(BoardEntity.builder().name("a").build());
        BoardEntity boardEntity3 = entityManager.persist(BoardEntity.builder().name("c").build());

        //when
        List<BoardEntity> boards = boardRepository.findBoardEntitiesByOrderByName();

        //then
        assertThat(boards).containsExactly(boardEntity2, boardEntity1, boardEntity3);
    }
}