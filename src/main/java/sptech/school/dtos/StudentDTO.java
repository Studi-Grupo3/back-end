package sptech.school.dtos;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record StudentDTO(
        String name,

        @Email(message = "The email is invalid")
        String email,

        @CPF(message = "The CPF is invalid")
        String cpf,

        String password,

        String responsibleCellphone
) {}
