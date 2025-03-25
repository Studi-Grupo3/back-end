package sptech.school.domain.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import sptech.school.domain.enumerated.AppointmentStatus;
import sptech.school.domain.enumerated.PaymentStatus;

import java.time.LocalDateTime;

public record AppointmentDTO(
          @NotNull Integer idStudent
        , @NotNull Integer idTeacher
        , @Future LocalDateTime dateTime
        , @NotNull Double lessonDuration
        , AppointmentStatus status
        , String location
        , PaymentStatus paymentStatus
) {}