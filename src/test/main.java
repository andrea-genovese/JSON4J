package test;
import java.util.Map;

import main.JSON;
public class Main {
    //private static final String o1 = "{\"abc\":1}";
    private static final String o2 = " { \"abc\" : [ 1 , 2 , 3 ] , \"def\" : { } , \"ghi\" : { \"a\" : \"A\" , \"b\" : [ { } , { } ] } } ";
    public static void main(String[] args) {

        try {
            Object o = JSON.parse(o2);
            if(o instanceof Map) {
                Map<String, Object> m = (Map<String, Object>) o;
                System.out.println(
                    m.get("abc")
                );
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
