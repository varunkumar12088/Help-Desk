package com.learning.controller;

import com.learning.model.User;
import com.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable(value = "email") String email) {
        User user = userService.getByEmail(email);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") String id) {
        User user = userService.getById(id);
        return ResponseEntity.ok().body(user);
    }
}
