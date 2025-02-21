package sptech.school.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.school.dtos.UserDTO;
import sptech.school.entities.User;
import sptech.school.exceptions.UserException;
import sptech.school.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(@Valid UserDTO userDTO) {
        return userRepository.save(new User(userDTO));
    }

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    public User update(UserDTO updateUserDTO, Integer id) {
        User updatedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserException("Usuário com o id %d não está registrado!".formatted(id)));

        if (updateUserDTO.username() != null && !updateUserDTO.username().isBlank()) {
            updatedUser.setUsername(updateUserDTO.username());
        }
        if (updateUserDTO.email() != null && !updateUserDTO.email().isBlank()) {
            updatedUser.setEmail(updateUserDTO.email());
        }
        if (updateUserDTO.password() != null && !updateUserDTO.password().isBlank()) {
            updatedUser.setPassword(updateUserDTO.password());
        }
        if (updateUserDTO.role() != null && !updateUserDTO.role().isBlank()) {
            updatedUser.setRole(updateUserDTO.role());
        }

        return userRepository.save(updatedUser);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void delete(Integer id) {
            userRepository.deleteById(id);
    }
}
