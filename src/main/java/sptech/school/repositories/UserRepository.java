package sptech.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import sptech.school.entities.User;

@NoRepositoryBean
public interface UserRepository<T extends User> extends JpaRepository<T, Integer> {
    T findByEmailIgnoreCaseAndPasswordOrCpfAndPassword(String email, String password, String cpf, String password2);
}
