package json;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> pairs;

    public JsonObject(JsonPair... jsonPairs) {
        pairs = new ArrayList<JsonPair>();
        pairs.addAll(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        if (pairs.size() == 0) {
            return "{}";
        }
        StringBuilder resJson = new StringBuilder();
        for (JsonPair currJsonPair : pairs) {
            resJson.append(currJsonPair.key + ":" + currJsonPair.value.toJson() + ",");
        }
        resJson.deleteCharAt(resJson.length() - 1);
        return "{" + resJson.toString() + "}";
    }

    private JsonPair findPair(String key) {
        for (JsonPair currJsonPair : pairs) {
            if (currJsonPair.key.equals(key)) {
                return currJsonPair;
            }
        }
        return null;
    }

    public void add(JsonPair jsonPair) {
        pairs.add(jsonPair);
    }

    public Json find(String name) {
        JsonPair foundPair = findPair(name);
        if (foundPair == null) {
            return null;
        }
        return foundPair.value;
    }

    public JsonObject projection(String... names) {

        JsonObject resJsonObject = new JsonObject();
        for (String currName : names) {
            JsonPair pair = findPair(currName);
            if (pair != null) {
                resJsonObject.add(pair);
            }
        }
        return resJsonObject;
    }
}
