package src.serde;

public class PartialDeserialization<T> {
    private T result;
    private String remainder;

    public PartialDeserialization(T result, String remainder) {
        this.result = result;
        this.remainder = remainder;
    }

    public T getResult() {
        return this.result;
    }

    public String getRemainder() {
        return this.remainder;
    }
}
