package sptech.school.adapters.out.persistence;

import sptech.school.domain.entity.Student;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface StudentRepositoryJpa extends JpaUserRepository<Student> {
    Optional<Student> findByEmail(String email);
}
