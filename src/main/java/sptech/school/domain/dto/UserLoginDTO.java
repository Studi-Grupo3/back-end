package sptech.school.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record UserLoginDTO(
          @Email String email
        , @CPF String cpf
        , @NotBlank String password

) {}