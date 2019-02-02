package com.vastenly.taf.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.util.http.RestClient;
import java.io.IOException;

import static com.vastenly.taf.app.AppConstants.*;
import static com.vastenly.taf.system.AllureLogger.log;
import static org.testng.Assert.assertEquals;

public class PetStoreAPI {

    private static String postRequestURL_STORE_ORDER = PET_STORE_SERVER_URL + "/v2" + STORE_ORDER_URI;

    public static void checkAccessToTheHost() throws UnirestException {
        HttpResponse<String> response = RestClient.sendGetRequest(PET_STORE_SERVER_URL);
        assertEquals(response.getStatus(), 200);
        log("checkAccessToTheHost: " + PET_STORE_SERVER_URL + " | " + response.getStatusText());
    }

    public static String createOrder(String jsonString, int expectedStatus) throws UnirestException {
        log(jsonString);
        HttpResponse<JsonNode> response = RestClient.sendPostRequest(postRequestURL_STORE_ORDER, API_KEY, jsonString);
        assertEquals(response.getStatus(), expectedStatus);
        return response.getStatusText();
    }

    public static Order createOrder(Order order, int expectedStatus) throws UnirestException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        order = getPostResponseOrder(jsonString, expectedStatus);
        return order;
    }

    public static Order createOrderWithNoFields(Order order, int expectedStatus, JsonInclude.Include option) throws UnirestException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(option);
        String jsonString = objectMapper.writeValueAsString(order);
        order = getPostResponseOrder(jsonString, expectedStatus);
        return order;
    }

    public static Order getPostResponseOrder(String jsonString, int expectedStatus) throws UnirestException, IOException {
        log(jsonString);
        HttpResponse<JsonNode> response = RestClient.sendPostRequest(postRequestURL_STORE_ORDER, API_KEY, jsonString);
        assertEquals(response.getStatus(), expectedStatus);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Order order = objectMapper.readValue(response.getBody().getObject().toString(), Order.class);
        if (isEmpty(order)) order = null;
        return order;
    }

    public static boolean isEmpty(Order ord) {
        return (ord.id == null
                && ord.petId == null
                && ord.quantity == 0
                && ord.shipDate == null
                && ord.status == null
                && ord.complete == false)
                ? true : false;
    }

}