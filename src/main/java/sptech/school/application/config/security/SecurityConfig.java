package sptech.school.application.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sptech.school.application.config.security.user.details.service.StudentUserDetailsService;
import sptech.school.application.config.security.user.details.service.TeacherUserDetailsService;
import sptech.school.application.service.JwtService;
import sptech.school.domain.exception.CustomAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final StudentUserDetailsService studentUserDetailsService;
    private final TeacherUserDetailsService teacherUserDetailsService;

    public SecurityConfig(JwtService jwtService, StudentUserDetailsService studentUserDetailsService, TeacherUserDetailsService teacherUserDetailsService) {
        this.jwtService = jwtService;
        this.studentUserDetailsService = studentUserDetailsService;
        this.teacherUserDetailsService = teacherUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                )
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers("/students/login", "/students").permitAll()
                        .requestMatchers(("/teachers/login"), "/teachers").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, teacherUserDetailsService, studentUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}