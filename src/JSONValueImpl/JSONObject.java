package JSONValueImpl;
import java.util.Map;
import main.JSONValue;
import main.ParseRes;
/**
 * JSONObject
 */
public class JSONObject implements JSONValue {
    private Map<String, JSONValue> map;
    public JSONObject(Map<String, JSONValue> map) {
        this.map = map;
    }
    public JSONObject(String json) {
        
    }
    @Override
    public String stringify() {
        return null;
    }
    @Override
    public ParseRes parse(String json) {
        return null;
    }
}