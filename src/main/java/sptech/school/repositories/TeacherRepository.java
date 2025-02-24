package sptech.school.repositories;

import org.springframework.stereotype.Repository;
import sptech.school.entities.Teacher;

@Repository
public interface TeacherRepository extends UserRepository<Teacher> {
}
