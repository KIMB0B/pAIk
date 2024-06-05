package com.kimbob.pAIk.domain.ingredient.repository;

import com.kimbob.pAIk.domain.ingredient.entity.Ingredient;
import com.kimbob.pAIk.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
    List<Ingredient> findByUser(User user);
    Optional<Ingredient> findByIdAndUser(UUID id, User user);
}
