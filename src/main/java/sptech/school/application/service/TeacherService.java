package sptech.school.application.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.application.usecase.AbstractUserUseCase;
import sptech.school.domain.dto.TeacherDTO;
import sptech.school.domain.entity.Teacher;
import sptech.school.application.mappers.TeacherMapper;
import sptech.school.adapters.out.persistence.JpaUserRepository;

import java.util.List;

@Service
public class TeacherService extends AbstractUserUseCase<Teacher, TeacherDTO> {
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherService(JpaUserRepository<Teacher> repository) {
        super(repository);
    }

    public Teacher create(@Valid Teacher teacher) {
        return repository.save(teacher);
    }

    @Override
    public Teacher validateSpecify(TeacherDTO dto, Teacher targetUser) {
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