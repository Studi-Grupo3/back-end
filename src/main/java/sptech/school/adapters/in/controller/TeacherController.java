package sptech.school.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.web.multipart.MultipartFile;
import sptech.school.application.mappers.ResourceFileMapper;
import sptech.school.application.mappers.StudentMapper;
import sptech.school.application.mappers.TeacherMapper;
import sptech.school.application.service.JwtService;
import sptech.school.application.service.TeacherService;
import sptech.school.domain.dto.AuthResponseDTO;
import sptech.school.domain.dto.ResourceFileDTO;
import sptech.school.domain.dto.TeacherDTO;
import sptech.school.domain.dto.UserLoginDTO;
import sptech.school.domain.entity.ResourceFile;
import sptech.school.domain.entity.Teacher;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private ResourceFileMapper resourceFileMapper;

    @Autowired
    private JwtService jwtService;

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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid UserLoginDTO loginDTO) {
        Teacher teacherLogged = teacherService.login(loginDTO);
        String token = jwtService.generateToken(teacherLogged.getEmail(), teacherLogged.getName(), "TEACHER");
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(teacherLogged.getName(), teacherLogged.getCpf(), teacherLogged.getEmail(), token);

        return ResponseEntity.status(200).body(authResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Integer id) {
        teacherService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> findAllTeachers() {
        List<TeacherDTO> teacherDTOS = teacherService.listAll();
        return ResponseEntity.status(200).body(teacherDTOS);
    }

    @PostMapping("/upload-profile-photo")
    public ResponseEntity<@Valid ResourceFileDTO> uploadArquivo(
            @RequestParam("file") MultipartFile file) throws IOException {
        ResourceFile resourceFile = teacherService.saveFile(file);
        return ResponseEntity.ok(resourceFileMapper.toResponse(resourceFile));
    }
}