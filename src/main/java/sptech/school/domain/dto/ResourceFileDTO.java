package sptech.school.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ResourceFileDTO(
        Long id
        , @NotBlank String fileName
        , @NotBlank String fileType
        , @NotBlank Long fileSize
        , @NotBlank String formattedSize
) { }
