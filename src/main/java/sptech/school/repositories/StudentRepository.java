package sptech.school.repositories;

import org.springframework.stereotype.Repository;
import sptech.school.entities.Student;

@Repository
public interface StudentRepository extends UserRepository<Student>{
}
