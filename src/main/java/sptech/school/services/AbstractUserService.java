package sptech.school.services;

import jakarta.validation.Valid;
import sptech.school.dtos.UserLoginDTO;
import sptech.school.entities.User;
import sptech.school.exceptions.UserException;
import sptech.school.repositories.UserRepository;

public abstract class AbstractUserService<T extends User, DTO> implements UserService<T, DTO>  {
    protected final UserRepository<T> repository;

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
        return repository.findByEmailIgnoreCaseAndPasswordOrCpfAndPassword(user.email(), user.password(), user.cpf(), user.password());
    }

    protected abstract T validateSpecify(DTO dto, T userTarget);
}