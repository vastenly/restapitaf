package com.vastenly.taf.util.http;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RestClient {

    public static HttpResponse<JsonNode> sendPostRequest(String URL, String api_key, String body) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(URL)
                .header("api_key", api_key)
                .header("Content-type", "application/json")
                .body(body)
                .asJson();
        return response;
    }

    public static HttpResponse<String> sendGetRequest(String URL) throws UnirestException {
        HttpResponse<String> response = Unirest.get(URL)
                .header("accept", "application/json")
                .asString();
        return response;
    }

}