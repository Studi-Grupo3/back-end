package sptech.school.application.mappers;

import jakarta.validation.Valid;
import org.mapstruct.*;
import sptech.school.application.mappers.utils.StringMapperUtil;
import sptech.school.domain.dto.ResourceFileDTO;
import sptech.school.domain.dto.StudentDTO;
import sptech.school.domain.entity.Student;

@Mapper(componentModel = "spring", uses = StringMapperUtil.class)
public interface StudentMapper {
    @Valid StudentDTO toDto(Student student);
    Student toEntity(@Valid StudentDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(@Valid StudentDTO studentDTO, @MappingTarget Student student);
}