package sptech.school.application.service;

import jakarta.validation.Valid;
import sptech.school.adapters.out.persistence.JpaUserRepository;
import sptech.school.application.usecase.UserUseCase;
import sptech.school.domain.dto.UserLoginDTO;
import sptech.school.domain.entity.User;
import sptech.school.domain.exception.UserException;

public abstract class AbstractUserUseCase<T extends User, DTO> implements UserUseCase<T, DTO> {
    protected final JpaUserRepository<T> repository;

    public AbstractUserUseCase(JpaUserRepository<T> repository) {
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
}