package test;

import main.JSON;
import main.JSONArray;
import main.JSONObject;
public class Main {
    //private static final String o1 = "{\"abc\":1}";
    private static final String o2 = " { \"abc\" : [ 1 , 2 , 3 ] , \"def\" : { } , \"ghi\" : { \"a\" : \"A\" , \"b\" : [ { } , { } ] } } ";
    public static void main(String[] args) {

        try {
            Object o = JSON.parse(o2);
            if(o instanceof JSONArray) {
                JSONObject m = (JSONObject) o;
                System.out.println(
                    m
                );
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
