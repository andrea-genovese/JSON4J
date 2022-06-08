package Parsers;

public class ValueParser {
    
    public static Object parse(char[] json) {        
        ParseResult p = parse(json, 0);
        if (p != null && ignoreWhiteSpaces(json, p.index()) == json.length) {
            return p.value();
        }
        throw new InvalidJSONException("Not a valid JSON string");

    }

    public static ParseResult parse(char[] json, int index) {
        index = ignoreWhiteSpaces(json, index);
        if(index >= json.length) {
            return null;
        }
        ParseResult res = BooleanParser.parse(json, index);
        if (res != null)
            return res;

        res = NumberParser.parse(json, index);
        if (res != null)
            return res;

        res = NullParser.parse(json, index);
        if (res != null)
            return res;

        res = StringParser.parse(json, index);
        if (res != null)
            return res;
        res = ArrayParser.parse(json, index);
        if (res != null)
            return res;
        res = ObjectParser.parse(json, index);
        
        if (res != null)
            return res;
        return null;
    }
    
    static int ignoreWhiteSpaces(char[] json, int index){
        for (int i = index; i <json.length; i++){
            if(!isWhiteSpace(json[i])) {
                return i;
            }
        }
        return json.length;
    }

    private static boolean isWhiteSpace(char c) {
        return c==' ' || c=='\t' || c == '\n' || c=='\r';
    }
}
