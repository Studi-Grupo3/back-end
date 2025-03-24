package sptech.school.adapters.out.persistence;

import sptech.school.domain.entity.Teacher;

import java.util.Optional;

public interface TeacherRepositoryJpa extends JpaUserRepository<Teacher> {
    Optional<Teacher> findByEmail(String email);
}
