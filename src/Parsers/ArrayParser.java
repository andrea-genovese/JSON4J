package Parsers;

import java.util.ArrayList;
import java.util.List;

public class ArrayParser {
    public static ParseResult parse(char[] json, int index) {
        if (json[index++] != '[')
            return null;
        index = ValueParser.ignoreWhiteSpaces(json, index);
        if(index >= json.length) {
            return null;
        }
        List<Object> list = new ArrayList<>();
        if (json[index] == ']') {
            return new ParseResult(list, index + 1);
        }

        for (int i = index; i < json.length; i++) {
            ParseResult res = ValueParser.parse(json, i);
            if (res == null) {
                return null;
            }
            list.add(res.value());
            i = ValueParser.ignoreWhiteSpaces(json, res.index());

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
