package sptech.school.mappers;

import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import sptech.school.dtos.AppointmentDTO;
import sptech.school.entities.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper{
    @Valid
    AppointmentDTO toDto(Appointment appointment);
    Appointment toEntity(@Valid AppointmentDTO dto);
}
