package sptech.school.services;

import org.springframework.stereotype.Service;
import sptech.school.entities.User;

@Service
public interface UserService<T extends User> {
    T update(T user);
    T findUserByEmail(T user);
    T delete(T user);
    T listUsers();
}
