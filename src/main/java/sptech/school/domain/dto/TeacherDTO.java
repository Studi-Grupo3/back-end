package sptech.school.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import sptech.school.domain.enumerated.Discipline;

public record TeacherDTO(
          String name
        , @Email String email
        , @CPF String cpf
        , String password
        , Discipline discipline
) { }