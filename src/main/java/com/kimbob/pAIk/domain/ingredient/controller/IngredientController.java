package com.kimbob.pAIk.domain.ingredient.controller;

import com.kimbob.pAIk.domain.ingredient.dto.IngredientDTO;
import com.kimbob.pAIk.domain.ingredient.entity.Ingredient;
import com.kimbob.pAIk.domain.ingredient.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientService.findByCurrentUser());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable UUID id) {
        Optional<Ingredient> ingredient = ingredientService.findByIdAndCurrentUser(id);
        return ingredient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ResponseEntity.ok(ingredientService.create(ingredientDTO));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable UUID id, @RequestBody IngredientDTO ingredientDTO) {
        Optional<Ingredient> updatedIngredient = ingredientService.update(id, ingredientDTO);
        return updatedIngredient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable UUID id) {
        ingredientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
