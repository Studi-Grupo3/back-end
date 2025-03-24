package sptech.school.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;
    private String fileType;
    private String fileLocation;
    private Long fileSize;

    public Content() {}

    public Content(String fileName, String fileType, String fileLocation, Long fileSize) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileLocation = fileLocation;
        this.fileSize = fileSize;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public Long getFileSize() {
        return fileSize;
    }
}