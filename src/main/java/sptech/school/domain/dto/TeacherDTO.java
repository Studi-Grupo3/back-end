package sptech.school.domain.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import sptech.school.domain.enumerated.Discipline;

public record TeacherDTO(
        String name,

        @Email(message = "The email is invalid")
        String email,

        @CPF(message = "The CPF is invalid")
        String cpf,

        String password,

        Discipline discipline
) {

}