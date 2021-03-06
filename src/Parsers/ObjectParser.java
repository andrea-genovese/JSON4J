package Parsers;

import main.JSONHashMap;
import main.JSONObject;

public class ObjectParser {
    public static ParseResult parse(char[] json, int index) {
        if (json[index++] != '{') {
            return null;
        }
        index = ValueParser.ignoreWhiteSpaces(json, index);
        if(index >= json.length) {
            return null;
        }
       JSONObject obj = new JSONHashMap();
        if (json[index] == '}')
            return new ParseResult(obj, index+1);
        for (; index < json.length; index++) {
            index = ValueParser.ignoreWhiteSpaces(json, index);
            ParseResult key = StringParser.parse(json, index);
            if (key == null)
                return null;
            index = ValueParser.ignoreWhiteSpaces(json, key.index());
            
            if(index>= json.length) {
                return null;
            }
            if(json[index] != ':') {
                return null;
            }
            index++;
            ParseResult val = ValueParser.parse(json, index);
            if(val == null) {
                return null;
            }
            index = ValueParser.ignoreWhiteSpaces(json, val.index());
            if(index >= json.length) {
                return null;
            }
            obj.put((String)key.value(), val.value());
            if(json[index] == '}') {
                return new ParseResult(obj, index+1);
            }
            if(json[index] != ',') {
                return null;
            }
        }

        return null;
    }
}
