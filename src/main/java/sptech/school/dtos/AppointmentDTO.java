package sptech.school.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentDTO(
        @NotNull Integer idStudent,
        @NotNull Integer idTeacher,
        @Future(message = "The date must be in the future.") LocalDateTime dateTime
) {
}