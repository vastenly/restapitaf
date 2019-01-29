package com.vastenly.taf.util.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.app.Order;
import com.vastenly.taf.util.ObjectMapperConfiguration;
import org.json.JSONObject;

public class PetStoreAPI {

    private static String host = "http://petstore.swagger.io";


    public static ObjectMapper getObjectMapper() {
        ObjectMapperConfiguration mapperConfig = new ObjectMapperConfiguration();
        return mapperConfig.serializingObjectMapper();
    }

    public static void checkAccessToTheHost() throws UnirestException {
        RestClient.sendGetRequest(host);
    }

    public static JSONObject createOrder(String pathName, Order order) throws UnirestException, JsonProcessingException {
        pathName = "https://petstore.swagger.io/v2" + pathName;

        ObjectMapper mapper = getObjectMapper();
        String jsonString = mapper.writeValueAsString(order);

        return RestClient.sendPostRequest(pathName, "vastenly", jsonString, 200);
    }

}