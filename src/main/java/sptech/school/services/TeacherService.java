package sptech.school.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.dtos.TeacherDTO;
import sptech.school.entities.Appointment;
import sptech.school.entities.Student;
import sptech.school.entities.Teacher;
import sptech.school.exceptions.UserException;
import sptech.school.mappers.TeacherMapper;
import sptech.school.repositories.UserRepository;

import java.util.List;

@Service
public class TeacherService extends AbstractUserService<Teacher, TeacherDTO> {
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherService(UserRepository<Teacher> repository) {
        super(repository);
    }

    public Teacher create(@Valid Teacher teacher) {
        return repository.save(teacher);
    }

    @Override
    protected Teacher validateSpecify(TeacherDTO dto, Teacher targetUser) {
        teacherMapper.updateTeacherFromDto(dto, targetUser);
        return targetUser;
    }

    public Teacher findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found."));
    }

    public List<TeacherDTO> listAll() {
        return repository.findAll()
                .stream().map(teacherMapper::toDto).toList();
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment not found.");
        }
        repository.deleteById(id);
    }
}