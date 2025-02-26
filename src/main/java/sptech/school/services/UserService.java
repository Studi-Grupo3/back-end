package sptech.school.services;

import org.springframework.stereotype.Service;
import sptech.school.dtos.UserLoginDTO;
import sptech.school.entities.User;

@Service
public interface UserService<T extends User, DTO> {
    T update(DTO user, Integer id);
    T login(UserLoginDTO user);
}
