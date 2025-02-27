package sptech.school.mappers;

import jakarta.validation.Valid;
import org.mapstruct.*;
import sptech.school.dtos.StudentDTO;
import sptech.school.entities.Student;

@Mapper(componentModel = "spring", uses = StringMapper.class)
public interface StudentMapper {
    @Valid StudentDTO toDto(Student student);
    Student toEntity(@Valid StudentDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(@Valid StudentDTO studentDTO, @MappingTarget Student student);

}