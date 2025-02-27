package sptech.school.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_teacher")
public class Teacher extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The specialty is mandatory")
    private String specialty;

    public Teacher() {
    }

    public Teacher(String name, String email, String cpf, String password, String specialty) {
        super(name, email, cpf, password);
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", specialty='" + specialty + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}