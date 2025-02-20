package sptech.school.dtos;

import jakarta.validation.constraints.Email;

public record UserDTO(String username
        , @Email String email
        , String password
        , String role) {

}
