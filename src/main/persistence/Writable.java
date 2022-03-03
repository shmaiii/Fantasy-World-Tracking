package persistence;

import org.json.JSONObject;

//citation: modelled on the JsonSerializationDemo project provided on EDx
public interface Writable {
    JSONObject toJson();
}
