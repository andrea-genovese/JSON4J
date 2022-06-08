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

}
