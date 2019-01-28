package com.vastenly.taf.util.http;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class PetStoreAPI {

    private static String host = "http://petstore.swagger.io";

    public static void checkAccessToTheHost() throws UnirestException {
        RestClient.sendGetRequest(host);
    }

    public static JSONObject createOrder(String pathName, String body) throws Exception {
        pathName = "https://petstore.swagger.io/v2" + pathName;
        return RestClient.sendPostRequest(pathName, "vastenly", body, 200);
    }

}