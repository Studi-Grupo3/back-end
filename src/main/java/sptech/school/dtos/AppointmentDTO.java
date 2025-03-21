package sptech.school.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;
import sptech.school.enums.AppointmentStatus;
import sptech.school.enums.PaymentStatus;

import java.time.LocalDateTime;

public record AppointmentDTO(
        @NotNull
        Integer idStudent,

        @NotNull
        Integer idTeacher,

        @Future(message = "The date must be in the future.")
        LocalDateTime dateTime,

        @NotNull
        Double lessonDuration,

        AppointmentStatus status,

        String location,

        PaymentStatus paymentStatus
) {}