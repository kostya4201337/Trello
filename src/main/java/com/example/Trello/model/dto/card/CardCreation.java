package com.example.Trello.model.dto.card;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CardCreation {

    @NotBlank(message = "Name is mandatory")
    private final String name;

    @NotBlank(message = "Description is mandatory")
    private final String description;
}
