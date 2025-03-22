package sptech.school.application.mappers;

import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import sptech.school.domain.dto.AppointmentDTO;
import sptech.school.domain.entity.Appointment;

@Mapper(componentModel = "spring")
public interface AppointmentMapper{
    @Valid
    @Mapping(source = "student.id", target = "idStudent")
    @Mapping(source = "teacher.id", target = "idTeacher")

    AppointmentDTO toDto(Appointment appointment);
    Appointment toEntity(@Valid AppointmentDTO dto);
}
