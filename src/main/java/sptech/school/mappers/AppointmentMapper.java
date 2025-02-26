package sptech.school.mappers;

import org.mapstruct.Mapper;
import sptech.school.dtos.AppointmentDTO;
import sptech.school.entities.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper{
    AppointmentDTO toDto(Appointment appointment);
    Appointment toEntity(AppointmentDTO dto);
}
