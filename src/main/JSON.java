package main;
import Parsers.ValueParser;

public class JSON {
    public static String stringify(Object obj){
        if(obj instanceof String) {
            return "\""+obj+"\"";
        }
        return "";
    }
    public static Object parse(String json) {
        return ValueParser.parse(json.toCharArray());
    }

}
