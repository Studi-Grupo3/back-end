package sptech.school.application.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import sptech.school.adapters.out.persistence.JpaUserRepository;
import sptech.school.application.usecase.UserUseCase;
import sptech.school.domain.dto.UserLoginDTO;
import sptech.school.domain.entity.User;
import sptech.school.domain.exception.UserException;

public abstract class AbstractUserUseCase<T extends User, DTO> implements UserUseCase<T, DTO> {
    protected final JpaUserRepository<T> repository;

    @Autowired
    private JwtService jwtService;

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
        T foundUser = repository.findByEmailIgnoreCaseAndPasswordOrCpfAndPassword(user.email(), user.password(), user.cpf(), user.password());
        if (foundUser == null) {
            throw new UserException("Invalid email/CPF or password");
        }

        return foundUser;
    }
}