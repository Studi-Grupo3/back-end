package sptech.school.mappers;

import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sptech.school.dtos.AppointmentDTO;
import sptech.school.entities.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper{
    @Valid
    @Mapping(source = "student.id", target = "idStudent")
    @Mapping(source = "teacher.id", target = "idTeacher")

    AppointmentDTO toDto(Appointment appointment);
    Appointment toEntity(@Valid AppointmentDTO dto);
}
