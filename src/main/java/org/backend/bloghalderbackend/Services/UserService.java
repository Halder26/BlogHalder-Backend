package org.backend.bloghalderbackend.Services;

import jakarta.transaction.Transactional;
import org.backend.bloghalderbackend.DTOs.UserDTO;
import org.backend.bloghalderbackend.Entities.User;
import org.backend.bloghalderbackend.Repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        for (User user: userRepository.findAll())
        {
            users.add(new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getCountry()));
        }
        return ResponseEntity.ok(users);
    }

    @Transactional
    public void deleteUser(String email) {
        userRepository.removeUserByEmail(email);
    }

    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getCountry());
    }

    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getCountry());
    }
}

