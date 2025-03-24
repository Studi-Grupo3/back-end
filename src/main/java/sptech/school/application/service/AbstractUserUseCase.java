package sptech.school.application.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.adapters.out.persistence.JpaResourceFileRepository;
import sptech.school.adapters.out.persistence.JpaUserRepository;
import sptech.school.application.usecase.StorageServiceUseCase;
import sptech.school.application.usecase.UserUseCase;
import sptech.school.domain.dto.UserLoginDTO;
import sptech.school.domain.entity.ResourceFile;
import sptech.school.domain.entity.User;
import sptech.school.domain.exception.UserException;

import java.io.IOException;
import java.time.LocalDateTime;

public abstract class AbstractUserUseCase<T extends User, DTO> implements UserUseCase<T, DTO> {
    protected final JpaUserRepository<T> repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private StorageServiceUseCase storageService;

    @Autowired
    private JpaResourceFileRepository jpaResourceFileRepository;

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
        foundUser.setLastLogin(LocalDateTime.now());
        return foundUser;
    }

    public ResourceFile saveFile(MultipartFile file) throws IOException {
        String location = storageService.saveFile(file);
        ResourceFile resourceFile = new ResourceFile(
                file.getOriginalFilename()
                , file.getContentType()
                , location
                , file.getSize()
        );
        return jpaResourceFileRepository.save(resourceFile);
    }
}