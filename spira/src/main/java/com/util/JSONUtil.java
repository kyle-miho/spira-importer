package com.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONUtil {
    public static JSONObject findJSONObject(JSONArray jsonArray, Object name, String key) {
        JSONObject jsonObject = null;

        for (int i = 0; i < jsonArray.length(); ++i) {
            if (jsonArray.getJSONObject(i).get(key).equals(name)) {
                jsonObject = jsonArray.getJSONObject(i);
            }
        }

        return jsonObject;
    }

}
