package com.kimbob.pAIk.domain.ingredient.service;

import com.kimbob.pAIk.domain.ingredient.dto.IngredientDTO;
import com.kimbob.pAIk.domain.ingredient.entity.Ingredient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientService {
    List<Ingredient> findByCurrentUser();
    Optional<Ingredient> findByIdAndCurrentUser(UUID id);
    Ingredient create(IngredientDTO ingredientDTO);
    Optional<Ingredient> update(UUID id, IngredientDTO ingredientDTO);
    void delete(UUID id);
}
