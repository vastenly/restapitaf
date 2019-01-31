package com.vastenly.taf.app;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.util.http.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class PetStoreAPI {

    private static final Logger log = LoggerFactory.getLogger(PetStoreAPI.class);

    private static String host = "http://petstore.swagger.io";

    public static void checkAccessToTheHost() throws UnirestException {
        RestClient.sendGetRequest(host);
    }

    public static String createOrder(int expectedStatus) throws UnirestException {
        String pathName = host + "/v2" + "/store/order";
        String jsonString = "{,}";
        HttpResponse<JsonNode> response = RestClient.sendPostRequest(pathName, "vastenly", jsonString);
        assertEquals(response.getStatus(), expectedStatus);
        return response.getStatusText();
    }

    public static Order createOrder(Order order, int expectedStatus) throws UnirestException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        order = getResponse(jsonString, expectedStatus);
        return order;
    }

    public static Order createOrderWithNoFields(Order order, int expectedStatus) throws UnirestException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        String jsonString = objectMapper.writeValueAsString(order);
        order = getResponse(jsonString, expectedStatus);
        return order;
    }

    public static Order getResponse(String jsonString, int expectedStatus) throws UnirestException, IOException {
        String pathName = host + "/v2" + "/store/order";
        log.info("jsonString " + jsonString);
        HttpResponse<JsonNode> response = RestClient.sendPostRequest(pathName, "vastenly", jsonString);
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