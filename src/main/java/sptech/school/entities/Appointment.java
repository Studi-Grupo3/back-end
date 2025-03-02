package sptech.school.entities;

import jakarta.persistence.*;
import sptech.school.enums.AppointmentStatus;
import sptech.school.enums.PaymentStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "fkStudent", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idTeacher", nullable = false)
    private Teacher teacher;

    @Column(columnDefinition = "DATETIME(0)")
    private LocalDateTime dateTime;

    private Double lessonDuration;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    private String location;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Appointment() {
    }

    public Appointment(Student student, Teacher teacher, LocalDateTime dateTime, Double lessonDuration, String location) {
        this.student = student;
        this.teacher = teacher;
        this.dateTime = dateTime;
        this.lessonDuration = lessonDuration;
        this.location = location;
        this.status = AppointmentStatus.SCHEDULED;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(Double lessonDuration) {
        this.lessonDuration = lessonDuration;
    }
}