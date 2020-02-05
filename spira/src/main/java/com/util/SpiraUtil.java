package com.util;

import com.constants.SpiraConstants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Map;

public class SpiraUtil {
    public static JSONObject getAPIRequestJSONObject(String endPoint) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(endPoint);

        _setBaseHeaders(httpGet);

        System.out.println(httpGet.getAllHeaders().toString());

        HttpResponse httpResponse = httpClient.execute(httpGet);

        return HttpUtil.getJSONObject(httpResponse);
    }

    public static JSONArray getAPIRequest(String endPoint) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(endPoint);

        _setBaseHeaders(httpGet);

        System.out.println(httpGet.getAllHeaders().toString());

        HttpResponse httpResponse = httpClient.execute(httpGet);

        return HttpUtil.getJSONArray(httpResponse);
    }

    public static JSONObject postAPIRequest(
            String endPoint, JSONObject parameters)
        throws Exception {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(endPoint);

        _setBaseHeaders(httpPost);

        _setEntity(parameters, httpPost);

        System.out.println(httpPost.getAllHeaders().toString());

        HttpResponse httpResponse = httpClient.execute(httpPost);

        return HttpUtil.getJSONObject(httpResponse);
    }

    private static void _setEntity(
           JSONObject parameters, HttpPost httpPost) throws Exception {

        StringEntity stringEntity = new StringEntity(parameters.toString());

        httpPost.setEntity(stringEntity);
    }

    private static void _setBaseHeaders(HttpRequestBase httpRequestBase) {
        httpRequestBase.setHeader("Accept", "application/json");
        httpRequestBase.setHeader("api-key", SpiraConstants.API_KEY);
        httpRequestBase.setHeader("Content-Type", "application/json");
        httpRequestBase.setHeader("username", SpiraConstants.USERNAME);
    }
}
