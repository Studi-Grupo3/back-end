package sptech.school.services;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import sptech.school.dtos.UserLoginDTO;
import sptech.school.entities.User;
import sptech.school.exceptions.UserException;
import sptech.school.repositories.UserRepository;

public abstract class AbstractUserService<T extends User, DTO> implements UserService<T, DTO>  {
    protected final UserRepository<T> repository;

    @Autowired
    private JwtService jwtService;

    public AbstractUserService(UserRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T update(@Valid DTO dto, Integer id) {
        T userTarget = repository.findById(id)
                .orElseThrow(() -> new UserException("User not found"));

        userTarget = validateSpecify(dto, userTarget);

        return repository.save(userTarget);
    }


    @Override
    public T login(@Valid UserLoginDTO user) {
        T foundUser = repository.findByEmailIgnoreCaseAndPasswordOrCpfAndPassword(user.email(), user.password(), user.cpf(), user.password());
        if (foundUser == null) {
            throw new UserException("Invalid email/CPF or password");
        }

        return foundUser;
    }

    protected abstract T validateSpecify(DTO dto, T userTarget);
}