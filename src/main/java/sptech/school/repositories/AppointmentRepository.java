package sptech.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sptech.school.entities.Appointment;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    boolean existsByStudentIdAndTeacherIdAndDateTime(Integer userStudentId, Integer userTeacherId, LocalDateTime dateTime);
}