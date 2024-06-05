package com.kimbob.pAIk.domain.ingredient.service;

import com.kimbob.pAIk.domain.ingredient.dto.IngredientDTO;
import com.kimbob.pAIk.domain.ingredient.entity.Ingredient;
import com.kimbob.pAIk.domain.ingredient.repository.IngredientRepository;
import com.kimbob.pAIk.domain.user.entity.User;
import com.kimbob.pAIk.domain.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientServiceImpl implements IngredientService {

    private static final Logger log = LoggerFactory.getLogger(IngredientServiceImpl.class);
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Ingredient> findByCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ingredientRepository.findByUser(user);
    }

    @Override
    public Optional<Ingredient> findByIdAndCurrentUser(UUID id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ingredientRepository.findByIdAndUser(id, user);
    }

    @Override
    public Ingredient create(IngredientDTO ingredientDTO) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Ingredient ingredient = new Ingredient();
        ingredient.setUser(user);
        ingredient.setName(ingredientDTO.getName());
        ingredient.setVolume(ingredientDTO.getVolume());
        ingredient.setVolumeUnit(ingredientDTO.getVolumeUnit());

        return ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> update(UUID id, IngredientDTO ingredientDTO) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Ingredient> optionalIngredient = ingredientRepository.findByIdAndUser(id, user);

        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            if (ingredientDTO.getName() != null) {
                ingredient.setName(ingredientDTO.getName());
            }
            if (ingredientDTO.getVolume() != null) {
                ingredient.setVolume(ingredientDTO.getVolume());
            }
            if (ingredientDTO.getVolumeUnit() != null) {
                ingredient.setVolumeUnit(ingredientDTO.getVolumeUnit());
            }
            return Optional.of(ingredientRepository.save(ingredient));
        }
        return Optional.empty();
    }

    @Override
    public void delete(UUID id) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<Ingredient> optionalIngredient = ingredientRepository.findByIdAndUser(id, user);
        if (optionalIngredient.isPresent()) {
            ingredientRepository.delete(optionalIngredient.get());
        } else {
            throw new RuntimeException("Ingredient not found or you do not have access");
        }
    }
}
