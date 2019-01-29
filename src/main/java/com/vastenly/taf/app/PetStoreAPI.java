package com.vastenly.taf.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.util.http.RestClient;
import org.json.JSONObject;

public class PetStoreAPI {

    private static String host = "http://petstore.swagger.io";

    public static void checkAccessToTheHost() throws UnirestException {
        RestClient.sendGetRequest(host);
    }

    public static JSONObject createOrder(String pathName, Order order) throws UnirestException, JsonProcessingException {
        pathName = "https://petstore.swagger.io/v2" + pathName;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);

        return RestClient.sendPostRequest(pathName, "vastenly", jsonString, 200);
    }

}