package sptech.school.exceptions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ErrorResponse(@NotNull int status
        , @NotBlank String errorDescription
        , @NotBlank String errorMessage) {
}
