package sptech.school.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_teacher")
@Data
@NoArgsConstructor
public class Teacher extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The specialty is mandatory")
    private String specialty;

    public Teacher(String name, String email, String cpf, String password, String specialty) {
        super(name, email, cpf, password);
        this.specialty = specialty;
    }
}