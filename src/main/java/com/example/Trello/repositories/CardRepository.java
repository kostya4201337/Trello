package com.example.Trello.repositories;

import com.example.Trello.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findCardEntitiesByBoardIdOrderByCreatedAtDesc(long boardId);
}
