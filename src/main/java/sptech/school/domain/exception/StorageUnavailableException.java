package sptech.school.domain.exception;

public class StorageUnavailableException extends RuntimeException {
  public StorageUnavailableException(String message) {
    super(message);
  }
}
