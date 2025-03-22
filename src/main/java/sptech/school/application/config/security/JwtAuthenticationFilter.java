package sptech.school.application.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import sptech.school.application.config.security.user.details.service.StudentUserDetailsService;
import sptech.school.application.config.security.user.details.service.TeacherUserDetailsService;
import sptech.school.application.service.JwtService;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final TeacherUserDetailsService teacherUserDetailsService;
    private final StudentUserDetailsService studentUserDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, TeacherUserDetailsService teacherUserDetailsService, StudentUserDetailsService studentUserDetailsService) {
        this.jwtService = jwtService;
        this.studentUserDetailsService = studentUserDetailsService;
        this.teacherUserDetailsService = teacherUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);
        String role = jwtService.extractRole(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    "TEACHER".equalsIgnoreCase(role) ? teacherUserDetailsService.loadUserByUsername(email)
                            : "STUDENT".equalsIgnoreCase(role) ? studentUserDetailsService.loadUserByUsername(email)
                            : null;

            if (jwtService.validateToken(token)) {
                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, List.of(authority));

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        chain.doFilter(request, response);
    }
}

