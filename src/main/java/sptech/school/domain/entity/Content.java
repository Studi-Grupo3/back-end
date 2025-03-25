package sptech.school.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_content")
public class Content extends ResourceFile {
    @ManyToOne
    @JoinColumn(name = "fkStudent", nullable = false)
    @NotBlank
    private Student student;

    public Content() {}

    public Content(String fileName, String fileType, String fileLocation, Long fileSize) {
        super(fileName, fileType, fileLocation, fileSize);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}