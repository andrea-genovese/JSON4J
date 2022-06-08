package Symbols;

import java.util.ArrayList;
import java.util.List;

public class JSONArray {
    public static ParseResult parse(char[] json, int index) {
        if (json[index++] != '[')
            return null;
        index = JSONValue.ignoreWhiteSpaces(json, index);
        if(index >= json.length) {
            return null;
        }
        List<Object> list = new ArrayList<>();
        if (json[index] == ']') {
            return new ParseResult(list, index + 1);
        }

        for (int i = index; i < json.length; i++) {
            ParseResult res = JSONValue.parse(json, i);
            if (res == null) {
                return null;
            }
            list.add(res.value());
            i = JSONValue.ignoreWhiteSpaces(json, res.index());

            if(i >= json.length) {
                return null;
            }
            if (json[i] == ']')
                return new ParseResult(list, i+1);
            if (json[i] != ',') {
                return null;
            }
        }
        return null;
    }
}
