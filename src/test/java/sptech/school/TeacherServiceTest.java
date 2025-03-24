
package sptech.school;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;
import sptech.school.adapters.out.persistence.JpaUserRepository;
import sptech.school.application.mappers.TeacherMapper;
import sptech.school.application.service.TeacherService;
import sptech.school.domain.dto.TeacherDTO;
import sptech.school.domain.entity.Teacher;
import sptech.school.domain.enumerated.Discipline;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Dado o uso da TeacherService")
class TeacherServiceTest {

    @Mock private JpaUserRepository<Teacher> repository;
    @Mock private TeacherMapper teacherMapper;

    @InjectMocks
    private TeacherService teacherService;

    private AutoCloseable mocks;
    private Teacher teacher;
    private TeacherDTO dto;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        teacherService = new TeacherService(repository);
        ReflectionTestUtils.setField(teacherService, "teacherMapper", teacherMapper);
        teacher = new Teacher();
        dto = new TeacherDTO("Nome", "email@teste.com", "12345678900", "senha", Discipline.ART);
    }

    @Test
    @DisplayName("[1] - Deve criar professor com sucesso")
    void deveCriarProfessor() {
        when(repository.save(teacher)).thenReturn(teacher);

        Teacher salvo = teacherService.create(teacher);

        assertEquals(teacher, salvo);
    }

    @Test
    @DisplayName("[2] - Deve validar e atualizar professor via DTO")
    void deveValidarEDTO() {
        Teacher result = teacherService.validateSpecify(dto, teacher);

        verify(teacherMapper).updateTeacherFromDto(dto, teacher);
        assertEquals(teacher, result);
    }

    @Test
    @DisplayName("[3] - Deve retornar professor por ID")
    void deveBuscarPorId() {
        when(repository.findById(1)).thenReturn(Optional.of(teacher));

        Teacher encontrado = teacherService.findById(1);

        assertEquals(teacher, encontrado);
    }

    @Test
    @DisplayName("[4] - Deve lançar exceção ao buscar ID inexistente")
    void deveLancar404Busca() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
            () -> teacherService.findById(99));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
    }

    @Test
    @DisplayName("[5] - Deve listar todos professores convertidos para DTO")
    void deveListarTodos() {
        List<Teacher> lista = List.of(teacher, teacher);
        TeacherDTO dtoMock = new TeacherDTO("A", "B", "C", "D", Discipline.ART);

        when(repository.findAll()).thenReturn(lista);
        when(teacherMapper.toDto(any())).thenReturn(dtoMock);

        List<TeacherDTO> resultado = teacherService.listAll();

        assertEquals(2, resultado.size());
    }

    @Test
    @DisplayName("[6] - Deve deletar professor existente")
    void deveDeletarProfessor() {
        when(repository.existsById(1)).thenReturn(true);

        teacherService.delete(1);

        verify(repository).deleteById(1);
    }

    @Test
    @DisplayName("[7] - Deve lançar exceção ao deletar ID inexistente")
    void deveErroAoDeletar() {
        when(repository.existsById(42)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> teacherService.delete(42));
    }

    @Test
    @DisplayName("[8] - Deve validar DTO com campos nulos sem lançar erro")
    void validarComCamposNulos() {
        TeacherDTO dtoParcial = new TeacherDTO(null, null, null, null, null);

        Teacher res = teacherService.validateSpecify(dtoParcial, teacher);

        assertNotNull(res);
    }

    @Test
    @DisplayName("[9] - Deve tratar múltiplos professores na listagem")
    void listarMuitosProfessores() {
        List<Teacher> lista = Arrays.asList(new Teacher(), new Teacher(), new Teacher());
        when(repository.findAll()).thenReturn(lista);
        when(teacherMapper.toDto(any())).thenReturn(dto);

        List<TeacherDTO> resultado = teacherService.listAll();

        assertEquals(3, resultado.size());
    }

    @Test
    @DisplayName("[10] - Deve criar professor com senha padrão")
    void deveCriarComSenhaPadrao() {
        teacher.setPassword("padrao123");
        when(repository.save(any())).thenReturn(teacher);

        Teacher res = teacherService.create(teacher);

        assertEquals("padrao123", res.getPassword());
    }
}
