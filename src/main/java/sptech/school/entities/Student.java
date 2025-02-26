package sptech.school.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_student")
@Data
@NoArgsConstructor
public class Student extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The responsible cellphone is mandatory!")
    private String responsibleCellphone;

    public Student(String name, String email, String cpf, String password, String responsibleCellphone) {
        super(name, email, cpf, password);
        this.responsibleCellphone = responsibleCellphone;
    }
}