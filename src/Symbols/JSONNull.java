package Symbols;

public class JSONNull {
    public static ParseResult parse(char[] json, int index){
        if (json[index] == 'n' &&
                json[index+1] == 'u' &&
                json[index+2] == 'l' &&
                json[index+3] == 'l')
            return new ParseResult(null, index+4);
        return null;
    }
}
