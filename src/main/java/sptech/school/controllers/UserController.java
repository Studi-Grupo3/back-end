package sptech.school.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.dtos.UserDTO;
import sptech.school.entities.User;
import sptech.school.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDTO) {
        User createdUser = userService.create(userDTO);
        return ResponseEntity.ok().body(createdUser);
    }

    @GetMapping("buscar-por-id/{id}")
    private ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User searchedUser = userService.findById(id);
        return ResponseEntity.ok().body(searchedUser);
    }

    @GetMapping("buscar-por-email{email}")
    private ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        User searchedUser = userService.findByEmail(email);
        return ResponseEntity.ok().body(searchedUser);
    }

    @PutMapping("/{id}")
    private ResponseEntity<User> updateUser(@RequestBody UserDTO userDTO, @PathVariable Integer id) {
        User updatedUser = userService.update(userDTO, id);
        return ResponseEntity.ok().body(updatedUser);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listar")
    private ResponseEntity<List<User>> listUsers(){
        List<User> users = userService.listUsers();

        return ResponseEntity.ok().body(users);
    }
}