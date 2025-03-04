package sptech.school.mappers;

import org.springframework.stereotype.Component;

@Component
public class StringMapper {
    public String mapString(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
