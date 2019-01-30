package com.vastenly.taf.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.util.http.RestClient;

import java.io.IOException;

public class PetStoreAPI {

    private static String host = "http://petstore.swagger.io";

    public static void checkAccessToTheHost() throws UnirestException {
        RestClient.sendGetRequest(host);
    }

    public static Order createOrder(String pathName, Order order) throws UnirestException, IOException {
        pathName = "https://petstore.swagger.io/v2" + pathName;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);

        return RestClient.sendPostRequest(pathName, "vastenly", jsonString, 200);
    }

    public static void createOrderWithFail(String pathName, Order order) throws UnirestException, IOException {
        pathName = "https://petstore.swagger.io/v2" + pathName;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        RestClient.sendPostRequestWithFail(pathName, "vastenly", jsonString, 400);
    }

    public static void createOrderWithFail(String pathName, Order.OrderNoComplete order) throws UnirestException, IOException {
        pathName = "https://petstore.swagger.io/v2" + pathName;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        RestClient.sendPostRequestWithFail(pathName, "vastenly", jsonString, 400);
    }

    public static void createOrderWithFail(String pathName, Order.OrderNoId order) throws UnirestException, IOException {
        pathName = "https://petstore.swagger.io/v2" + pathName;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(order);
        RestClient.sendPostRequestWithFail(pathName, "vastenly", jsonString, 400);
    }

    public static void createOrderWithFail(String pathName) throws UnirestException, IOException {
        pathName = "https://petstore.swagger.io/v2" + pathName;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{}";
        RestClient.sendPostRequestWithFail(pathName, "vastenly", jsonString, 400);
    }

}