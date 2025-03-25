package sptech.school.application.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.adapters.out.persistence.JpaUserRepository;
import sptech.school.application.mappers.StudentMapper;
import sptech.school.application.usecase.AbstractUserUseCase;
import sptech.school.domain.dto.StudentDTO;
import sptech.school.domain.entity.Student;

import java.util.List;

@Service
public class StudentService extends AbstractUserUseCase<Student, StudentDTO> {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StudentService(JpaUserRepository<Student> repository) {
        super(repository);
    }

    public Student create(@Valid Student student) {
        String hashedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(hashedPassword);

        return repository.save(student);
    }

    @Override
    public Student validateSpecify(@Valid StudentDTO dto, Student targetUser) {
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