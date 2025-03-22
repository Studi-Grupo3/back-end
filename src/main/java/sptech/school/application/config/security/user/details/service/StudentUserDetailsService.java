package sptech.school.application.config.security.user.details.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sptech.school.adapters.out.persistence.StudentRepositoryJpa;

@Service
public class StudentUserDetailsService implements UserDetailsService {
    private final StudentRepositoryJpa studentRepository;

    public StudentUserDetailsService(StudentRepositoryJpa studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentRepository.findByEmail(email)
                .map(student -> User.builder()
                        .username(student.getEmail())
                        .password(student.getPassword())
                        .roles("STUDENT")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
