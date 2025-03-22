package sptech.school.application.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.domain.dto.StudentDTO;
import sptech.school.domain.entity.Student;
import sptech.school.application.mappers.StudentMapper;
import sptech.school.adapters.out.persistence.JpaUserRepository;

import java.util.List;

@Service
public class StudentService extends AbstractUserUseCase<Student, StudentDTO> {
    @Autowired
    private StudentMapper studentMapper;

    public StudentService(JpaUserRepository<Student> repository) {
        super(repository);
    }

    public Student create(@Valid Student student) {
        return repository.save(student);
    }

    @Override
    public Student validateSpecify(StudentDTO dto, Student targetUser) {
        studentMapper.updateStudentFromDto(dto, targetUser);
        return targetUser;
    }

    public Student findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found."));
    }

    public List<StudentDTO> listAll() {
        return repository.findAll()
                .stream().map(studentMapper::toDto).toList();
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found.");
        }
        repository.deleteById(id);
    }
}