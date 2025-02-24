package sptech.school.services;

import sptech.school.entities.User;
import sptech.school.repositories.UserRepository;

public abstract class AbstractUserService<T extends User> implements UserService<T>  {
    protected final UserRepository<T> repository;

    public AbstractUserService(UserRepository<T> repository) {
        this.repository = repository;
    }


    @Override
    public T update(T user) {
        validateCommon(user);
        validateSpecify(user);

        return repository.save(user);
    }

    public void validateCommon(T user){

    }

    protected abstract void validateSpecify(T user);
}