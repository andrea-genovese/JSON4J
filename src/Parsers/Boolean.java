package Parsers;

public class Boolean {
    public static ParseResult parse(char[] json, int index) {
        if (json[index] == 't' &&
                json[index + 1] == 'r' &&
                json[index + 2] == 'u' &&
                json[index + 3] == 'e')
            return new ParseResult(java.lang.Boolean.valueOf(true), index + 4);

        if (json[index] == 'f' &&
                json[index + 1] == 'a' &&
                json[index + 2] == 'l' &&
                json[index + 3] == 's' &&
                json[index + 4] == 'e')
            return new ParseResult(java.lang.Boolean.valueOf(false), index + 5);

        return null;
    }
}
