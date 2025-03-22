package sptech.school.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
public abstract class User {

    @NotBlank(message = "The name is mandatory")
    private String name;

    @Email(message = "The email is invalid")
    @NotBlank(message = "The email is mandatory")
    @Column(unique = true)
    private String email;

    @CPF(message = "The CPF is invalid")
    @NotBlank(message = "The CPF is mandatory")
    @Column(unique = true)
    private String cpf;

    @NotBlank(message = "The password is mandatory")
    private String password;

    public User() {
    }

    public User(String name, String email, String cpf, String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}