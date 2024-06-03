package com.kimbob.pAIk.domain.user.service;

import com.kimbob.pAIk.domain.user.entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleService {
    Optional<Role> findById(UUID id);
    Optional<Role> findByAuthority(String authority);
    List<Role> findAll();
    Role create(String authority);
    Optional<Role> update(UUID id, Role roleDetails);
    void delete(UUID id);
}