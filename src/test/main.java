package test;
import main.JSON;
public class main {
    private static final String o1 = "{\"abc\":1}";
    private static final String o2 = "{\"abc\":[1,2,3],\"def\":{},\"ghi\":{\"a\":\"A\",\"b\":[{},{}]}}";
    public static void main(String[] args) {

        try {
            System.out.println(JSON.parse(o2));
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
