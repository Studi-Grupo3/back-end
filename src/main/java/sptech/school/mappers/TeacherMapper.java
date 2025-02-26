package sptech.school.mappers;

import jakarta.validation.Valid;
import org.mapstruct.*;
import sptech.school.dtos.TeacherDTO;
import sptech.school.entities.Teacher;

@Mapper(componentModel = "spring", uses = StringMapper.class)
public interface TeacherMapper {
    TeacherDTO toDto(Teacher teacher);
    Teacher toEntity(TeacherDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(@Valid TeacherDTO dto, @MappingTarget Teacher teacher);

}