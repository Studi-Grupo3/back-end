package sptech.school.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Discipline {
    PORTUGUESE("Português"),
    MATHEMATICS("Matemática"),
    GEOGRAPHY("Geografia"),
    HISTORY("História"),
    SOCIOLOGY("Sociologia"),
    PHILOSOPHY("Filosofia"),
    ART("Arte"),
    ENGLISH("Inglês"),
    SPANISH("Espanhol"),
    SCIENCE("Ciências"),
    BIOLOGY("Biologia"),
    CHEMISTRY("Química"),
    PHYSICS("Física"),
    LITERACY("Alfabetização");

    private final String description;

    Discipline(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Discipline fromString(String value) {
        for (Discipline discipline : Discipline.values()) {
            if (discipline.name().equalsIgnoreCase(value) || discipline.getDescription().equalsIgnoreCase(value)) {
                return discipline;
            }
        }
        throw new IllegalArgumentException("Invalid discipline: " + value);
    }
}