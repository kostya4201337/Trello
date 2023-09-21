package com.example.Trello.model.dto.card;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

public record CardCreation(@NotBlank(message = "Name is mandatory") String name, @NotBlank(message = "Description is mandatory") String description) {

}
