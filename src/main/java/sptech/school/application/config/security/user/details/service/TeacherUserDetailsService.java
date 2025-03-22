package sptech.school.application.config.security.user.details.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sptech.school.adapters.out.persistence.TeacherRepositoryJpa;

@Service
public class TeacherUserDetailsService implements UserDetailsService {
    private final TeacherRepositoryJpa teacherRepository;

    public TeacherUserDetailsService(TeacherRepositoryJpa teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return teacherRepository.findByEmail(email)
                .map(student -> User.builder()
                        .username(student.getEmail())
                        .password(student.getPassword())
                        .roles("TEACHER")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
