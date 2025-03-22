package sptech.school.services;

import jakarta.servlet.http.HttpServletResponse;
import sptech.school.dtos.UserLoginDTO;
import sptech.school.entities.User;

public interface UserService<T extends User, DTO> {
    T update(DTO user, Integer id);
    T login(UserLoginDTO user);
}
