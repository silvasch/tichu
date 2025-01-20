package src.serde;

public class PartialDeserialization<T> {
    private T result;
    private String remainder;

    public PartialDeserialization(T result, String remainder) {
        this.result = result;
        this.remainder = remainder;
    }

    public T deserialize() throws DeserializationException {
        if (this.remainder != "") {
            throw new DeserializationException(
                    String.format(
                            "the deserializeation left '%s' as the remainder.", this.remainder));
        }

        return this.result;
    }

    public T getResult() {
        return this.result;
    }

    public String getRemainder() {
        return this.remainder;
    }
}
