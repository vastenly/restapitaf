package com.vastenly.taf.orders;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.BaseTest;
import com.vastenly.taf.app.Order;
import com.vastenly.taf.app.enums.OrderStatus;
import com.vastenly.taf.util.StringUtils;
import com.vastenly.taf.util.http.PetStoreAPI;

import static org.testng.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import com.fasterxml.jackson.core.JsonProcessingException;


@Description("Order Test")
public class OrderTest extends BaseTest {

    private final Logger log = LoggerFactory.getLogger(OrderTest.class);

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {333, 3333, 3, "2019-01-27T17:06:56.745Z", OrderStatus.PLACED.status(), false},
                {444, 4444, 4, "2019-01-28T17:06:56.745Z", OrderStatus.PLACED.status(), false},
        };
    }

    @Title("Order Create Test")
    @Test(dataProvider = "data")
    public void orderCreateTest(Integer id, Integer petId, Integer quantity, String shipDate, String status, boolean complete) throws JsonProcessingException, UnirestException {

        Order order = new Order();
        order.id = Long.valueOf(id);
        order.petId = Long.valueOf(petId);
        order.quantity = quantity;
        order.shipDate = shipDate;
        order.status = status;
        order.complete = complete;

        responseBody = PetStoreAPI.createOrder("/store/order", order);
        log.info(responseBody.toString());
        shipDate = StringUtils.replaceLast(shipDate, "Z", "");

        assertEquals(responseBody.get("id"), id);
        assertEquals(responseBody.get("petId"), petId);
        assertEquals(responseBody.get("quantity"), quantity);
        assertTrue(responseBody.get("shipDate").toString().contains(shipDate));
        assertEquals(responseBody.get("status"), status);
        assertEquals(responseBody.get("complete"), complete);
    }
}
