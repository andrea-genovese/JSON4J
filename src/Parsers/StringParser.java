package Parsers;

public class StringParser {
    private static String createString(char[] arr, int start, int end) {
        char[] subarray = new char[end-start];
        for (int i = 0; i < subarray.length; i++) {
            subarray[i] = arr[start+i];
        }
        return new String(subarray);
    }
    public static ParseResult parse(char[] json, int index) {
        if(index >= json.length - 1 || json[index] != '"') { //controlla se c'è spazio per una stringa e se il primo carattere è '"'
            return null;
        }
        for(int i = index+1; i < json.length; i++) {
            if(json[i] == '"' && json[i-1] != '\\' ) {

                return new ParseResult(createString(json, index+1, i), i+1);
            }
        }
        return null;
    }
}