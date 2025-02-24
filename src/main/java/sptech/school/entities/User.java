package sptech.school.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import sptech.school.dtos.UserDTO;

@MappedSuperclass
@Data
public abstract class User {

    @NotBlank(message = "O nome não pode ser vazio")
    private String name;

    @Email(message = "O email está inválido")
    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    private String email;

    @CPF(message = "O cpf está inválido")
    @NotBlank(message = "O cpf é obrigatório")
    private String cpf;

    @NotNull
    private String password;
}