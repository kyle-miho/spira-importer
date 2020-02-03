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
import org.json.JSONObject;

import java.util.Map;

public class SpiraUtil {
    public static HttpResponse postAPIRequest(
            String endPoint, JSONObject parameters, String requestType)
        throws Exception {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(endPoint);

        _setBaseHeaders(httpPost);

        _setEntity(parameters, httpPost);

        System.out.println(httpPost.getAllHeaders());

        return httpClient.execute(httpPost);
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
