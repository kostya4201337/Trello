package com.example.Trello.mappers;

import com.example.Trello.model.dto.board.BoardCreation;
import com.example.Trello.model.entity.BoardEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BoardMapperTest {
    @InjectMocks
    BoardMapper boardMapper;

    @Test
    void should_mapBoardEntity_when_boardCreationPassed() {
        //given
        BoardCreation boardCreation = new BoardCreation("a", "b");

        //when
        BoardEntity boardEntity = boardMapper.map(boardCreation);

        //then
        BoardEntity expectedBoardEntity = new BoardEntity("a", "b");

        assertThat(boardEntity).isEqualTo(expectedBoardEntity);
    }
}