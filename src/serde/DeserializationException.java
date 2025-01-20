package src.serde;

public class DeserializationException extends Exception {
  public DeserializationException() {}

  public DeserializationException(String message) {
    super(message);
  }

  public DeserializationException(Exception e) {
    super(e);
  }
}
