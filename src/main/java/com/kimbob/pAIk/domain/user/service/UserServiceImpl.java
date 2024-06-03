package com.kimbob.pAIk.domain.user.service;

import com.kimbob.pAIk.domain.user.dto.UserSignUpDTO;
import com.kimbob.pAIk.domain.user.entity.Role;
import com.kimbob.pAIk.domain.user.entity.User;
import com.kimbob.pAIk.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserSignUpDTO userSignUpDto, Set<String> roleNames) {
        Set<Role> roles = roleNames.stream()
                .map(roleName -> roleService.findByAuthority(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());
        User user = userSignUpDto.toUser(roles);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> update(UUID id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setBirthday(userDetails.getBirthday());
            user.setRoles(userDetails.getRoles());
            user.setEnabled(userDetails.isEnabled());
            return userRepository.save(user);
        });
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
