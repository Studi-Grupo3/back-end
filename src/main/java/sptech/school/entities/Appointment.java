package sptech.school.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_student", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "id_teacher", nullable = false)
    private Teacher teacher;

    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime dateTime;

    public Appointment(Student student, Teacher teacher, LocalDateTime dateTime) {
        this.student = student;
        this.teacher = teacher;
        this.dateTime = dateTime;
    }
}