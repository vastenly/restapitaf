package com.vastenly.taf.orders;

import com.vastenly.taf.BaseTest;
import com.vastenly.taf.app.Order;
import com.vastenly.taf.app.TestData;
import com.vastenly.taf.app.enums.OrderStatus;
import com.vastenly.taf.util.StringUtils;
import com.vastenly.taf.util.http.PetStoreAPI;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Description("Order Test")
public class OrderTest extends BaseTest {

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {333, 3333, 3, "2019-01-27T17:06:56.745Z", OrderStatus.PLACED.status(), false},
                {444, 4444, 4, "2019-01-28T17:06:56.745Z", OrderStatus.PLACED.status(), false},
        };
    }

    @Title("Order Create Test")
    @Test(dataProvider = "data")
    public void orderCreateTest(Integer id, Integer petId, Integer quantity, String shipDate, String status, boolean complete) throws JsonProcessingException {

        ObjectMapper mapper = TestData.getObjectMapper();

        Order order = new Order();
        order.id = Long.valueOf(id);
        order.petId = Long.valueOf(petId);
        order.quantity = quantity;
        order.shipDate = shipDate;
        order.status = status;
        order.complete = complete;

        String jsonString = mapper.writeValueAsString(order);
        JSONObject responseBody = null;
        try {
            responseBody = PetStoreAPI.createOrder("/store/order", jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(responseBody.toString());

        shipDate = StringUtils.replaceLast(shipDate, "Z", "");

        Assert.assertEquals(responseBody.get("id"), id);
        Assert.assertEquals(responseBody.get("petId"), petId);
        Assert.assertEquals(responseBody.get("quantity"), quantity);
        Assert.assertTrue(responseBody.get("shipDate").toString().contains(shipDate));
        Assert.assertEquals(responseBody.get("status"), status);
        Assert.assertEquals(responseBody.get("complete"), complete);
    }
}
