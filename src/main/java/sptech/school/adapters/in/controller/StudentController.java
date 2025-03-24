package sptech.school.adapters.in.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.application.mappers.ResourceFileMapper;
import sptech.school.application.mappers.StudentMapper;
import sptech.school.application.service.JwtService;
import sptech.school.application.service.StudentService;
import sptech.school.domain.dto.AuthResponseDTO;
import sptech.school.domain.dto.ResourceFileDTO;
import sptech.school.domain.dto.StudentDTO;
import sptech.school.domain.dto.UserLoginDTO;
import sptech.school.domain.entity.ResourceFile;
import sptech.school.domain.entity.Student;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ResourceFileMapper resourceFileMapper;

    @Autowired
    private JwtService jwtService;

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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid UserLoginDTO loginDTO) {
        StudentDTO studentLogged = studentMapper.toDto(studentService.login(loginDTO));
        String token = jwtService.generateToken(studentLogged.email(), studentLogged.name(), "STUDENT");
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(studentLogged.name(), studentLogged.cpf(), studentLogged.email(), token);

        return ResponseEntity.status(200).body(authResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<StudentDTO>> findAll() {
        List<StudentDTO> studentDTOS = studentService.listAll();

        return ResponseEntity.status(200).body(studentDTOS);
    }

    @PostMapping("/upload-profile-photo")
    public ResponseEntity<@Valid ResourceFileDTO> uploadArquivo(
            @RequestParam("file") MultipartFile file) throws IOException {
        ResourceFile resourceFile = studentService.saveFile(file);
        return ResponseEntity.ok(resourceFileMapper.toResponse(resourceFile));
    }
}