package sptech.school.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.dtos.TeacherDTO;
import sptech.school.dtos.UserLoginDTO;
import sptech.school.entities.Teacher;
import sptech.school.mappers.TeacherMapper;
import sptech.school.services.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;

    @PostMapping
    public ResponseEntity<Teacher> createUser(@RequestBody @Valid TeacherDTO teacherDTO) {
        Teacher teacherCreated = teacherService.create(teacherMapper.toEntity(teacherDTO));
        System.out.println(teacherCreated.toString());
        return ResponseEntity.status(201).body(teacherCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable Integer id) {
        Teacher teacherSearched = teacherService.findById(id);
        return ResponseEntity.status(200).body(teacherMapper.toDto(teacherSearched));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@PathVariable Integer id, @RequestBody @Valid TeacherDTO teacherDTO) {
        Teacher teacherUpdated = teacherService.update(teacherDTO, id);
        return ResponseEntity.status(200).body(teacherMapper.toDto(teacherUpdated));
    }

    @PostMapping("/login")
    public ResponseEntity<TeacherDTO> login(@RequestBody @Valid UserLoginDTO loginDTO) {
        Teacher teacherLogged = teacherService.login(loginDTO);
        return ResponseEntity.status(200).body(teacherMapper.toDto(teacherLogged));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        teacherService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TeacherDTO>> findAllTeachers() {
        List<TeacherDTO> teacherDTOS = teacherService.listAll();
        return ResponseEntity.status(200).body(teacherDTOS);
    }
}
