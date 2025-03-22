package sptech.school.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.domain.entity.Appointment;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    boolean existsByStudentIdAndTeacherIdAndDateTime(Integer userStudentId, Integer userTeacherId, LocalDateTime dateTime);
}