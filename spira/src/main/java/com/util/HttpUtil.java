package com.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUtil {
    public static JSONArray getJSONArray(HttpResponse httpResponse) throws Exception {
        String json = EntityUtils.toString(httpResponse.getEntity());

        json = sanitize(json);

        try {
            return new JSONArray(json);
        } catch (JSONException jsonException) {
            System.out.println(json);

            throw new JSONException(jsonException);
        }

    }

    public static JSONObject getJSONObject(HttpResponse httpResponse) throws Exception {
        String json = EntityUtils.toString(httpResponse.getEntity());

        json = sanitize(json);

        try {
            return new JSONObject(json);
        } catch (JSONException jsonException) {
            System.out.println(json);

            throw new JSONException(jsonException);
        }

    }

    private static String sanitize(String dirty) {
        if (dirty.startsWith("\uFEFF")) {
            dirty = dirty.substring(1);
        }

        return dirty;
    }
}
