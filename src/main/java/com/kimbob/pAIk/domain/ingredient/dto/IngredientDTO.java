package com.kimbob.pAIk.domain.ingredient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

    private String name;
    private Double volume;
    private String volumeUnit;

}
