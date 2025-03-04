package sptech.school.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sptech.school.enums.Discipline;

@Entity
@Table(name = "tb_teacher")
public class Teacher extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Discipline discipline;

    public Teacher() {
    }

    public Teacher(String name, String email, String cpf, String password, Discipline discipline) {
        super(name, email, cpf, password);
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", discipline='" + discipline + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}