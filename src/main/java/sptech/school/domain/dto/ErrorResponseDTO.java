package sptech.school.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ErrorResponseDTO(
          @NotNull int status
        , @NotBlank String error
        , @NotBlank String message) {
}
