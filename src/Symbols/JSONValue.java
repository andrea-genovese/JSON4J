package Symbols;

public interface JSONValue {

    public static Object parse(String json) {
        ParseResult p = parse(json.toCharArray(), 0);
        if (p != null && p.index() == json.length()) {
            return p.value();
        }
        throw new IllegalArgumentException("Not a valid JSON String");

    }

    public static ParseResult parse(char[] json, int index) {
        index = ignoreWhiteSpaces(json, index);
        if(index >= json.length) {
            return null;
        }
        ParseResult res = JSONBoolean.parse(json, index);
        if (res != null)
            return res;

        res = JSONNumber.parse(json, index);
        if (res != null)
            return res;

        res = JSONNull.parse(json, index);
        if (res != null)
            return res;

        res = JSONString.parse(json, index);
        if (res != null)
            return res;
        res = JSONArray.parse(json, index);
        if (res != null)
            return res;
        res = JSONObject.parse(json, index);
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
