package sptech.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.entities.User;

@Repository
public interface UserRepository<T extends User> extends JpaRepository<T, Integer> {
    User findByEmailContainingIgnoreCase(String email);
    
    User findByEmailIgnoreCaseOrCpfAndPassword(String email, String cpf, String password);
}
