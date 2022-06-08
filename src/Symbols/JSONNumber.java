package Symbols;

public class JSONNumber implements JSONValue {    

    private static Number createNumber(char[] arr, int start, int end) {
        char[] sub = new char[end - start];
        for (int i = 0; i < sub.length; i++) {
            sub[i] = arr[start + i];
        }
        String str = new String(sub);
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return Double.parseDouble(str);
        }
    }

    public static ParseResult parse(char[] json, int index) {
        int i = index;
        for (; i < json.length; i++) {
            if (!isAllowed(json[i])) {
                break;
            }
        }
        try {
            return new ParseResult(createNumber(json, index, i), i);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static boolean isAllowed(char c) {
        return c >= '0' && c <= '9' ||
                c == 'e' ||
                c == 'E' ||
                c == '+' ||
                c == '-' ||
                c == '.';
    }
}
