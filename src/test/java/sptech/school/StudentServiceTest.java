
package sptech.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.application.service.StudentService;
import sptech.school.domain.dto.StudentDTO;
import sptech.school.domain.entity.Student;
import sptech.school.application.mappers.StudentMapper;
import sptech.school.adapters.out.persistence.JpaUserRepository;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Dado o uso da StudentService")
class StudentServiceTest {

    @Mock
    private JpaUserRepository<Student> repository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    private AutoCloseable mocks;

    private StudentDTO dtoExemplo;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(studentService, "studentMapper", studentMapper);
        dtoExemplo = new StudentDTO(
            "Nome Teste",
            "email@exemplo.com",
            "123.456.789-09",
            "senhaSegura",
            "11999999999"
        );
    }

    @Test
    @DisplayName("[1] - Quando um novo estudante for criado, ele deve ser salvo e retornado")
    void deveCriarEstudanteComSucesso() {
        Student estudante = new Student();
        when(repository.save(estudante)).thenReturn(estudante);

        Student resultado = studentService.create(estudante);

        assertEquals(estudante, resultado, "Deveria retornar o mesmo estudante salvo");
        verify(repository).save(estudante);
    }

    @Test
    @DisplayName("[2] - Quando validateSpecify for chamado, o estudante deve ser atualizado com os dados do DTO")
    void deveValidarEAtualizarEstudanteComDTO() {
        Student alvo = new Student();

        Student resultado = studentService.validateSpecify(dtoExemplo, alvo);

        verify(studentMapper).updateStudentFromDto(dtoExemplo, alvo);
        assertEquals(alvo, resultado, "Deveria retornar o estudante atualizado");
    }

    @Test
    @DisplayName("[3] - Quando um estudante for encontrado pelo ID, ele deve ser retornado")
    void deveRetornarEstudantePorId() {
        Student estudante = new Student();
        when(repository.findById(1)).thenReturn(Optional.of(estudante));

        Student resultado = studentService.findById(1);

        assertEquals(estudante, resultado, "Deveria retornar o estudante encontrado pelo ID");
    }

    @Test
    @DisplayName("[4] - Quando um estudante não for encontrado pelo ID, deve lançar exceção NOT_FOUND")
    void deveLancarExcecaoQuandoEstudanteNaoEncontradoPorId() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(
            ResponseStatusException.class,
            () -> studentService.findById(99),
            "Deveria lançar NOT_FOUND quando estudante não existir"
        );

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode(), "Status HTTP esperado: 404");
        assertEquals("Student not found.", ex.getReason(), "Mensagem esperada: Student not found.");
    }

    @Test
    @DisplayName("[5] - Quando listar estudantes, deve retornar todos como DTOs")
    void deveListarTodosEstudantesComoDTOs() {
        List<Student> estudantes = List.of(new Student(), new Student());
        when(repository.findAll()).thenReturn(estudantes);
        when(studentMapper.toDto(any(Student.class))).thenReturn(dtoExemplo, dtoExemplo);

        List<StudentDTO> resultado = studentService.listAll();

        assertEquals(2, resultado.size(), "Deveria listar todos os estudantes convertidos em DTO");
        verify(studentMapper, times(2)).toDto(any(Student.class));
    }

    @Test
    @DisplayName("[6] - Quando deletar um estudante existente, a operação deve ser bem sucedida")
    void deveDeletarEstudanteExistente() {
        when(repository.existsById(1)).thenReturn(true);

        studentService.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    @DisplayName("[7] - Quando deletar um estudante inexistente, deve lançar exceção NOT_FOUND")
    void deveLancarExcecaoAoDeletarEstudanteInexistente() {
        when(repository.existsById(42)).thenReturn(false);

        ResponseStatusException ex = assertThrows(
            ResponseStatusException.class,
            () -> studentService.delete(42),
            "Deveria lançar NOT_FOUND ao tentar deletar estudante inexistente"
        );

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode(), "Status HTTP esperado: 404");
        assertEquals("Appointment not found.", ex.getReason(), "Mensagem esperada: Appointment not found.");
    }
}
