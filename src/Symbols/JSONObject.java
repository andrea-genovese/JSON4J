package Symbols;

import java.util.HashMap;
import java.util.Map;

public class JSONObject {
    public static ParseResult parse(char[] json, int index) {
        if (json[index++] != '{') {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        if (json[index] == '}')
            return new ParseResult(map, index+1);
        for (int i = index; i < json.length; i++) {
            ParseResult key = JSONString.parse(json, i);
            if (key == null)
                return null;
            i = key.index();
            
            if(i>= json.length) {
                return null;
            }
            if(json[i++] != ':') {
                return null;
            }
            ParseResult val = JSONValue.parse(json, i);
            if(val == null) {
                return null;
            }
            i = val.index();
            map.put((String)key.value(), val.value());
            if(i >= json.length) {
                return null;
            }
            if(json[i] == '}') {
                return new ParseResult(map, i+1);
            }
            if(json[i] != ',') {
                return null;
            }


        }

        return null;
    }
}
