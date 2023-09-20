package com.example.Trello.model.dto.board;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

public record BoardCreation(@NotBlank(message = "Name is mandatory") String name, @NotBlank(message = "Name is mandatory") String description) {

}