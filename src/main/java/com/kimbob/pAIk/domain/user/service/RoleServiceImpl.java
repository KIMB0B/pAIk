package com.kimbob.pAIk.domain.user.service;

import com.kimbob.pAIk.domain.user.entity.Role;
import com.kimbob.pAIk.domain.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        return roleRepository.findByAuthority(authority);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role create(String authority) {
        Role role = new Role(authority);
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> update(UUID id, Role roleDetails) {
        return roleRepository.findById(id).map(role -> {
            role.setAuthority(roleDetails.getAuthority());
            return roleRepository.save(role);
        });
    }

    @Override
    public void delete(UUID id) {
        roleRepository.deleteById(id);
    }
}
