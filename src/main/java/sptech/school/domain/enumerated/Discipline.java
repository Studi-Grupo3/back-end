package sptech.school.domain.enumerated;


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

    public String getDescription() {
        return description;
    }

}