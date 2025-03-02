package sptech.school.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sptech.school.entities.Appointment;
import sptech.school.entities.Student;
import sptech.school.entities.Teacher;
import sptech.school.enums.Discipline;
import sptech.school.repositories.AppointmentRepository;
import sptech.school.repositories.StudentRepository;
import sptech.school.repositories.TeacherRepository;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DataLoaderConfig {
    @Bean
    public CommandLineRunner loadMockData(TeacherRepository teacherRepository,
                                          StudentRepository studentRepository,
                                          AppointmentRepository appointmentRepository) {
        return args -> {
            Teacher teacher1 = new Teacher("Matheus Barros", "matheus.barros@gmail.com", "725.721.161-20", "password1", Discipline.GEOGRAPHY);
            Teacher teacher2 = new Teacher("Lucas felipe", "lucas.felipe@uol.com.br", "658.130.963-02", "password2", Discipline.HISTORY);
            Teacher teacher3 = new Teacher("Glauco Lima", "glauco.lima@outlook.com", "535.536.583-39", "password3", Discipline.MATHEMATICS);
            Teacher teacher4 = new Teacher("Ruiz Bottura", "ruiz.bottura@msn.com.br", "407.258.726-59", "password4", Discipline.PORTUGUESE);

            teacherRepository.saveAll(Arrays.asList(teacher1, teacher2, teacher3, teacher4));

            Student student1 = new Student("Pedro davis", "pedro.davis@gmail.com", "177.152.664-50", "password1", "9876543210");
            Student student2 = new Student("Matheus Essu", "matheus.essu@sptech.school", "480.270.815-72", "password2", "8765432109");
            Student student3 = new Student("Lucas Carminati", "lucas.csantos@sptech.school", "921.128.717-06", "password3", "7654321098");
            Student student4 = new Student("Travis Scoot", "travis.scoot@msn.com.br", "815.214.538-61", "password4", "6543210987");

            studentRepository.saveAll(Arrays.asList(student1, student2, student3, student4));

            Appointment appointment1 = new Appointment(student1, teacher1, LocalDateTime.now().plusDays(1), 1.5, "online");
            Appointment appointment2 = new Appointment(student2, teacher2, LocalDateTime.now().plusDays(2), 1.0, "online");
            Appointment appointment3 = new Appointment(student3, teacher3, LocalDateTime.now().plusDays(3), 2.0, "online");
            Appointment appointment4 = new Appointment(student4, teacher4, LocalDateTime.now().plusDays(4), 1.5, "online");

            appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2, appointment3, appointment4));
        };
    }
}

