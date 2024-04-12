package org.backend.bloghalderbackend.Controllers;

import org.backend.bloghalderbackend.DTOs.UserDTO;
import org.backend.bloghalderbackend.Entities.User;
import org.backend.bloghalderbackend.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("users/")
    public ResponseEntity<List<UserDTO>> getAllUsers()
    {
        List<UserDTO> users = new ArrayList<>();
        for (User user: userService.getAllUsers())
        {
            users.add(new UserDTO(user.getEmail(), user.getName(), user.getCountry()));
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId)
    {
        if(userService.getUserById(userId)==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("user/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email)
    {
        if(userService.getUserByEmail(email)==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}
