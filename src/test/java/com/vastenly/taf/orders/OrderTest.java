package com.vastenly.taf.orders;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.BaseTest;
import com.vastenly.taf.app.Order;
import com.vastenly.taf.app.enums.OrderStatus;
import com.vastenly.taf.util.StringUtils;
import com.vastenly.taf.app.PetStoreAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;

import static org.testng.Assert.*;


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
    public void orderCreateTest(Integer id, Integer petId, Integer quantity, String shipDate, String status, boolean complete) throws UnirestException, IOException {

        Order order = new Order(Long.valueOf(id), Long.valueOf(petId), quantity, shipDate, status, complete);
        Order responseOrder = PetStoreAPI.createOrder("/store/order", order);
        order.shipDate = StringUtils.replaceLast(shipDate, "Z", "+0000");
        assertEquals(responseOrder, order);
    }
}
