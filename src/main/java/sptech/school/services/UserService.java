package sptech.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.school.entities.User;
import sptech.school.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User createUserAccount(User newUser) {
        return userRepository.save(newUser);
    }

    private User findUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    private User updateUser(User user, Long id) {
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void deleteUser(Long id) {
            userRepository.deleteById(id);
    }
}
