package org.backend.bloghalderbackend.Services;

import jakarta.transaction.Transactional;
import org.backend.bloghalderbackend.Entities.User;
import org.backend.bloghalderbackend.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Transactional
    public void deleteUser(String email) {
        userRepository.removeUserByEmail(email);
    }
}

