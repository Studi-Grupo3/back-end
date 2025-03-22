package sptech.school.applitcation.mappers;

import jakarta.validation.Valid;
import org.mapstruct.*;
import sptech.school.domain.dto.TeacherDTO;
import sptech.school.domain.entity.Teacher;

@Mapper(componentModel = "spring", uses = StringMapper.class)
public interface TeacherMapper {
    @Valid TeacherDTO toDto(Teacher teacher);
    Teacher toEntity(@Valid TeacherDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTeacherFromDto(@Valid TeacherDTO dto, @MappingTarget Teacher teacher);

}