package Symbols;

import java.util.HashMap;
import java.util.Map;

public class JSONObject {
    public static ParseResult parse(char[] json, int index) {
        if (json[index++] != '{') {
            return null;
        }
        index = JSONValue.ignoreWhiteSpaces(json, index);
        if(index >= json.length) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        if (json[index] == '}')
            return new ParseResult(map, index+1);
        for (; index < json.length; index++) {
            index = JSONValue.ignoreWhiteSpaces(json, index);
            ParseResult key = JSONString.parse(json, index);
            if (key == null)
                return null;
            index = JSONValue.ignoreWhiteSpaces(json, key.index());
            
            if(index>= json.length) {
                return null;
            }
            if(json[index] != ':') {
                return null;
            }
            index++;
            ParseResult val = JSONValue.parse(json, index);
            if(val == null) {
                return null;
            }
            index = JSONValue.ignoreWhiteSpaces(json, val.index());
            if(index >= json.length) {
                return null;
            }
            map.put((String)key.value(), val.value());
            if(json[index] == '}') {
                return new ParseResult(map, index+1);
            }
            if(json[index] != ',') {
                return null;
            }
        }

        return null;
    }
}
