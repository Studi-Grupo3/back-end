package sptech.school.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    @NotBlank(message = "The name is mandatory")
    private String name;

    @Email(message = "The email is invalid")
    @NotBlank(message = "The email is mandatory")
    @Column(unique = true)
    private String email;

    @CPF(message = "The CPF is invalid")
    @NotBlank(message = "The CPF is mandatory")
    private String cpf;

    @NotBlank(message = "The password is mandatory")
    private String password;

    public abstract Integer getId();
}