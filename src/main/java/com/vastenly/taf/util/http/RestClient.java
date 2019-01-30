package com.vastenly.taf.util.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.app.Order;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class RestClient {

    public static Order sendPostRequest(String URL, String api_key, String body, int expectedStatus) throws
            UnirestException, IOException {

        HttpResponse<JsonNode> response = Unirest.post(URL)
                .header("api_key", api_key)
                .header("Content-type", "application/json")
                .body(body)
                .asJson();
        assertEquals(response.getStatus(), expectedStatus);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.getBody().getObject().toString(), Order.class);
    }

    public static void sendPostRequestWithFail(String URL, String api_key, String body, int expectedStatus) throws
            UnirestException {

        HttpResponse<JsonNode> response = Unirest.post(URL)
                .header("api_key", api_key)
                .header("Content-type", "application/json")
                .body(body)
                .asJson();
        assertEquals(response.getStatus(), expectedStatus);
    }

    public static void sendGetRequest(String URL) throws UnirestException {
        HttpResponse<String> response = Unirest.get(URL)
                .header("accept", "application/json")
                .asString();
        assertEquals(response.getStatus(), 200);
        System.out.println(response);
    }

}