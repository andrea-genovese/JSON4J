package Parsers;

public class InvalidJSONException extends RuntimeException {
    public InvalidJSONException() {
        super();
    }
    public InvalidJSONException(String msg) {
        super(msg);
    }
}
