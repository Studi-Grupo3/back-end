package sptech.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailContainingIgnoreCase(String email);
    
    User findByEmailContainingIgnoreCaseOrCpfAndPassword(String email, String cpf, String password);
}
