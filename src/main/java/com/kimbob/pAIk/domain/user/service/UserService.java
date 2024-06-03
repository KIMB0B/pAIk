package com.kimbob.pAIk.domain.user.service;

import com.kimbob.pAIk.domain.user.dto.UserSignUpDTO;
import com.kimbob.pAIk.domain.user.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserService {
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    User create(UserSignUpDTO userSignUpDto, Set<String> roleNames);
    Optional<User> update(UUID id, User userDetails);
    void delete(UUID id);
}
