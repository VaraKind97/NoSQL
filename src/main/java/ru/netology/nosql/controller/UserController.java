package ru.netology.nosql.controller;

import lombok.RequiredArgsConstructor;
import ru.netology.nosql.model.User;
import ru.netology.nosql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable String id, @Valid @RequestBody User userDetails) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userDetails.setId(id);
        User updatedUser = userRepository.save(userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    public List<User> searchUsersByName(@RequestParam String name) {
        return userRepository.findByNameContaining(name);
    }

    @GetMapping("/search/age")
    public List<User> searchUsersByAge(@RequestParam Integer age) {
        return userRepository.findByAge(age);
    }
}