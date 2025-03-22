package sptech.school.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import sptech.school.domain.entity.User;

@NoRepositoryBean
public interface JpaUserRepository<T extends User> extends JpaRepository<T, Integer> {
    T findByEmailIgnoreCaseAndPasswordOrCpfAndPassword(String email, String password, String cpf, String password2);
}
