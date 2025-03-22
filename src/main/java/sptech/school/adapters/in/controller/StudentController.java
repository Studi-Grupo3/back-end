package sptech.school.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.domain.dto.StudentDTO;
import sptech.school.domain.dto.UserLoginDTO;
import sptech.school.domain.entity.Student;
import sptech.school.application.mappers.StudentMapper;
import sptech.school.application.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

    @PostMapping
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO studentDTO) {
        Student studentCreated = studentService.create(studentMapper.toEntity(studentDTO));

        return ResponseEntity.status(201).body(studentMapper.toDto(studentCreated));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Integer id) {
        Student studentSearched = studentService.findById(id);

        return ResponseEntity.status(200).body(studentMapper.toDto(studentSearched));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @RequestBody @Valid StudentDTO studentDTO) {
        Student studentUpdated = studentService.update(studentDTO, id);

        return ResponseEntity.status(200).body(studentMapper.toDto(studentUpdated));
    }

    @PostMapping("/login")
    public ResponseEntity<StudentDTO> login(@RequestBody @Valid UserLoginDTO loginDTO) {
        StudentDTO studentLogged = studentMapper.toDto(studentService.login(loginDTO));
        return ResponseEntity.status(200).body(studentLogged);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> studentDTOS = studentService.listAll();

        return ResponseEntity.status(200).body(studentDTOS);
    }
}
