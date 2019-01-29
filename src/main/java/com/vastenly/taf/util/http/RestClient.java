package com.vastenly.taf.util.http;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import static org.testng.Assert.assertEquals;

//import static org.junit.Assert.assertEquals;

public class RestClient {

    public static JSONObject sendPostRequest(String URL, String api_key, String body, int expectedStatus) throws
            UnirestException {

        HttpResponse<JsonNode> response = Unirest.post(URL)
                .header("api_key", api_key)
                .header("Content-type", "application/json")
                .body(body)
                .asJson();
        assertEquals(response.getStatus(), expectedStatus);
        return response.getBody().getObject();
    }

    public static void sendGetRequest(String URL) throws UnirestException {
        HttpResponse<String> response = Unirest.get(URL)
                .header("accept", "application/json")
                .asString();
        assertEquals(response.getStatus(), 200);
        System.out.println(response);
    }

}