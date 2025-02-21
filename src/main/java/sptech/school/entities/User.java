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

@Entity
@Table(name = "tb_usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome não pode ser vazio")
    private String username;

    @Email(message = "O email está inválido")
    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    private String email;

    @CPF(message = "O cpf está inválido")
    @NotBlank
    private String cpf;

    @NotNull
    private String password;

    @NotNull
    private String role;

    public User(@Valid UserDTO userDTO) {
        this(null
                , userDTO.username()
                , userDTO.email()
                , userDTO.cpf()
                , userDTO.password()
                , userDTO.role());
    }
}
