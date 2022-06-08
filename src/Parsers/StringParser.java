package Parsers;

import java.util.regex.Pattern;

public class StringParser {
    private static final Pattern INVALID = Pattern.compile("(?<!\\\\)\\\\(?![\"\\\\/bfnrt]|u[\\da-f]{4})",
            Pattern.MULTILINE);
    private static final Pattern QUOT = Pattern.compile("\\\\\"", Pattern.MULTILINE);
    private static final Pattern B_SLASH = Pattern.compile("\\\\\\\\", Pattern.MULTILINE);
    private static final Pattern BACKSPACE = Pattern.compile("\\\\b", Pattern.MULTILINE);
    private static final Pattern FORMFEED = Pattern.compile("\\\\f", Pattern.MULTILINE);
    private static final Pattern LF = Pattern.compile("\\\\n", Pattern.MULTILINE);
    private static final Pattern CR = Pattern.compile("\\\\r", Pattern.MULTILINE);
    private static final Pattern TAB = Pattern.compile("\\\\t", Pattern.MULTILINE);
    private static final Pattern CODEPOINT = Pattern.compile("\\\\u([\\da-f]{4})", Pattern.MULTILINE);

    private static String createString(char[] arr, int start, int end) {
        char[] subarray = new char[end - start];
        for (int i = 0; i < subarray.length; i++) {
            subarray[i] = arr[start + i];
        }
        return unescape(new String(subarray));
    }

    private static String unescape(String string) {
        if (INVALID.matcher(string).find()) {
            throw new InvalidJSONException("Invalid escape");
        }
        string = QUOT.matcher(string).replaceAll("\"");
        string = B_SLASH.matcher(string).replaceAll("\\");
        string = BACKSPACE.matcher(string).replaceAll("\b");
        string = FORMFEED.matcher(string).replaceAll("\f");
        string = LF.matcher(string).replaceAll("\n");
        string = CR.matcher(string).replaceAll("\r");
        string = TAB.matcher(string).replaceAll("\t");
        return CODEPOINT.matcher(string).replaceAll(match -> 
            Character.toString(Integer.parseInt(match.group(1), 16))
        );
    }

    public static ParseResult parse(char[] json, int index) {
        if (index >= json.length - 1 || json[index] != '"') { // controlla se c'è spazio per una stringa e se il primo
                                                              // carattere è '"'
            return null;
        }
        for (int i = index + 1; i < json.length; i++) {
            if (json[i] == '"' && json[i - 1] != '\\') {

                return new ParseResult(createString(json, index + 1, i), i + 1);
            }
        }
        return null;
    }
}