package sptech.school.dtos;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

public record UserDTO(String username
        , @Email String email
        , @CPF String cpf
        , String password) {

}
