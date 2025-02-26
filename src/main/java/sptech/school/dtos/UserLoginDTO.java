package sptech.school.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UserLoginDTO(
        @NotBlank(message = "The email is mandatory")
        @Email(message = "The email is invalid")
        String email,

        @NotBlank(message = "The CPF is mandatory")
        @CPF(message = "The CPF is invalid")
        String cpf,

        @NotBlank(message = "The password is mandatory")
        String password
) {}