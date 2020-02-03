package com.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class HttpUtil {
    public static JSONArray buildJSONArray(HttpResponse httpResponse) throws IOException {
        String response = EntityUtils.toString(httpResponse.getEntity());

        return new JSONArray(response);
    }

    public static void printHttpResponse(HttpResponse httpResponse) throws Exception {
        String response = EntityUtils.toString(httpResponse.getEntity());

        System.out.println(response);
    }
}
