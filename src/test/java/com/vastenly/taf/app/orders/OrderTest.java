package com.vastenly.taf.app.orders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.vastenly.taf.BaseTest;
import com.vastenly.taf.app.Order;
import com.vastenly.taf.app.enums.OrderStatus;
import com.vastenly.taf.app.PetStoreAPI;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.IOException;

import static com.vastenly.taf.app.AppConstants.*;
import static com.vastenly.taf.app.TestData.*;
import static com.vastenly.taf.app.TestData.getOrderWithNoFields;
import static org.testng.Assert.*;


@Description("Order Test")
public class OrderTest extends BaseTest {

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
                {333, 3333, 3, "2019-01-27T17:06:56.745Z", OrderStatus.PLACED.status(), false},
                {444, 4444, 4, "2019-01-28T17:06:56.745Z", OrderStatus.PLACED.status(), false},
        };
    }


    @Title("orderCreate_ValidData")
    @Test(dataProvider = "data", priority = 1)
    public void orderCreate_ValidData(Integer id, Integer petId, Integer quantity, String shipDate, String status, boolean complete) throws UnirestException, IOException {
        Order order = new Order(Long.valueOf(id), Long.valueOf(petId), quantity, shipDate, status, complete);
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_id_minimumValue")
    @Test(priority = 1)
    public void orderCreate_id_minimumValue() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.id = 1L;
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_id_maximumValue")
    @Test(priority = 1)
    public void orderCreate_id_maximumValue() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.id = Long.MAX_VALUE;
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_petId_minimumValue")
    @Test(priority = 1)
    public void orderCreate_petId_minimumValue() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.petId = 1L;
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_petId_maximumValue")
    @Test(priority = 1)
    public void orderCreate_petId_maximumValue() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.petId = Long.MAX_VALUE;
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_quantity_minimumValue")
    @Test(priority = 1)
    public void orderCreate_quantity_minimumValue() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.quantity = 1;
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_quantity_maximumValue")
    @Test(priority = 1)
    public void orderCreate_quantity_maximumValue() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.quantity = Integer.MAX_VALUE;
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_shipDate_currentDate")
    @Test(priority = 1)
    public void orderCreate_shipDate_currentDate() throws UnirestException, IOException {
        Order order = getOrder_ShipDate_DaysToIncrement(0);
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }

    @Title("orderCreate_shipDate_futureDate")
    @Test(priority = 1)
    public void orderCreate_shipDate_futureDate() throws UnirestException, IOException {
        Order order = getOrder_ShipDate_DaysToIncrement(1);
        Order responseOrder = PetStoreAPI.createOrder(order, 200);
        assertEquals(responseOrder, order);
    }


    //-----------------------------------------------------------------
    //Negative tests
    //
    @Title("orderCreate_failOnDuplicate")
    @Test(priority = 499)
    public void orderCreate_failOnDublicate() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        PetStoreAPI.createOrder(order, 200);
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_fail_NoFields")
    @Test(priority = 499)
    public void orderCreate_fail_NoFields() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(getOrderWithNoFields(), 400, JsonInclude.Include.NON_NULL.NON_DEFAULT);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_failOnNoData")
    @Test(priority = 499)
    public void orderCreate_failOnNoValues() throws UnirestException, IOException {
        Order order = new Order();
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_failOnNull")
    @Test(priority = 499)
    public void orderCreate_failOnNull() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(null, 500, JsonInclude.Include.NON_DEFAULT);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_failOnEmptyObjectWithComma")
    @Test(priority = 499)
    public void orderCreate_failOnEmptyObjectWithComma() throws UnirestException {
        String statusText = PetStoreAPI.createOrder("{,}", 400);
        assertEquals(statusText, ERROR_BAD_REQUEST);
    }

    @Title("orderCreate_id_failOnZero")
    @Test(priority = 499)
    public void orderCreate_id_failOnZero() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.id = 0L;
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_id_failOnOutOfBoundary")
    @Test(priority = 499)
    public void orderCreate_id_failOnOutOfBoundary() throws UnirestException {
        String statusText = PetStoreAPI.createOrder(jsonString_id_outOfLongMax, 400);
        assertEquals(statusText, ERROR_BAD_REQUEST);
    }

    @Title("orderCreate_id_fail_NegativeNumber")
    @Test(priority = 499)
    public void orderCreate_id_fail_NegativeNumber() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_Id_NegativeNumber(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_id_fail_ValueNull")
    @Test(priority = 499)
    public void orderCreate_id_fail_ValueNull() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_Id_NoValue(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_id_fail_NoField")
    @Test(priority = 499)
    public void orderCreate_id_fail_NoField() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(getOrder_Id_NoValue(), 400, JsonInclude.Include.NON_NULL);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_petId_failOnZero")
    @Test(priority = 499)
    public void orderCreate_petId_failOnZero() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.petId = 0L;
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_petId_failOnOutOfBoundary")
    @Test(priority = 499)
    public void orderCreate_petId_failOnOutOfBoundary() throws UnirestException {
        String statusText = PetStoreAPI.createOrder(jsonString_petId_outOfLongMax, 400);
        assertEquals(statusText, ERROR_BAD_REQUEST);
    }

    @Title("orderCreate_petId_fail_NegativeNumber")
    @Test(priority = 499)
    public void orderCreate_petId_fail_NegativeNumber() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_PetId_NegativeNumber(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_petId_fail_ValueNull")
    @Test(priority = 499)
    public void orderCreate_petId_fail_ValueNull() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_PetId_NoValue(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_petId_fail_NoField")
    @Test(priority = 499)
    public void orderCreate_petId_fail_NoField() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(getOrder_PetId_NoValue(), 400, JsonInclude.Include.NON_NULL);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_quantity_failOnZero_DefaltValue")
    @Test(priority = 499)
    public void orderCreate_quantity_failOnZero() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_Quantity_DefaultValue(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_quantity_failOnOutOfBoundary")
    @Test(priority = 499)
    public void orderCreate_quantity_failOnOutOfBoundary() throws UnirestException {
        String statusText = PetStoreAPI.createOrder(jsonString_quantity_outOfIntegerMax, 400);
        assertEquals(statusText, ERROR_BAD_REQUEST);
    }

    @Title("orderCreate_quantity_fail_NegativeNumber")
    @Test(priority = 499)
    public void orderCreate_quantity_fail_NegativeNumber() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_Quantity_NegativeNumber(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_quantity_fail_ValueSpecialSymbol")
    @Test(priority = 499)
    public void orderCreate_quantity_fail_ValueSpecialSymbol() throws UnirestException {
        String statusText = PetStoreAPI.createOrder(jsonString_quantity_specialSymbol, 400);
        assertEquals(statusText, ERROR_BAD_REQUEST);
    }

    @Title("orderCreate_quantity_fail_NoField")
    @Test(priority = 499)
    public void orderCreate_quantity_fail_NoField() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(getOrder_Quantity_DefaultValue(), 400, JsonInclude.Include.NON_DEFAULT);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_shipDate_failOnPastDate")
    @Test(priority = 499)
    public void orderCreate_shipDate_failOnPastDate() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_ShipDate_DaysToIncrement(-1), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_shipDate_failOnEmptyField")
    @Test(priority = 499)
    public void orderCreate_shipDate_failOnEmptyField() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.shipDate = "";
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_shipDate_fail_SpaceValue")
    @Test(priority = 499)
    public void orderCreate_shipDate_fail_SpaceValue() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_ShipDate_SpaceValue(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_shipDate_fail_ValueNull")
    @Test(priority = 499)
    public void orderCreate_shipDate_fail_ValueNull() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_ShipDate_NoValue(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_shipDate_fail_ValueSpecialSymbol")
    @Test(priority = 499)
    public void orderCreate_shipDate_fail_ValueSpecialSymbol() throws UnirestException {
        String statusText = PetStoreAPI.createOrder(jsonString_shipDate_specialSymbol, 400);
        assertEquals(statusText, ERROR_BAD_REQUEST);
    }

    @Title("orderCreate_shipDate_fail_NoField")
    @Test(priority = 499)
    public void orderCreate_shipDate_fail_NoField() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(getOrder_ShipDate_NoValue(), 400, JsonInclude.Include.NON_NULL);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_status_failOnApproved")
    @Test(priority = 499)
    public void orderCreate_status_failOnApproved() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.status = OrderStatus.APPROVED.status();
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_status_failOnDelivered")
    @Test(priority = 499)
    public void orderCreate_status_failOnDelivered() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.status = OrderStatus.DELIVERED.status();
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_status_failOnEmptyField")
    @Test(priority = 499)
    public void orderCreate_status_failOnEmptyField() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.setStatus("");
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_status_fail_SpaceValue")
    @Test(priority = 499)
    public void orderCreate_status_fail_SpaceValue() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_Status_SpaceValue(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_status_fail_ValueNull")
    @Test(priority = 499)
    public void orderCreate_status_fail_ValueNull() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrder(getOrder_Status_NoValue(), 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_status_fail_ValueSpecialSymbol")
    @Test(priority = 499)
    public void orderCreate_status_fail_ValueSpecialSymbol() throws UnirestException {
        String statusText = PetStoreAPI.createOrder(jsonString_status_specialSymbol, 400);
        assertEquals(statusText, ERROR_BAD_REQUEST);
    }

    @Title("orderCreate_status_fail_NoField")
    @Test(priority = 499)
    public void orderCreate_status_fail_NoField() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(getOrder_Status_NoValue(), 400, JsonInclude.Include.NON_NULL);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_complete_failOnTrue")
    @Test(priority = 499)
    public void orderCreate_complete_failOnTrue() throws UnirestException, IOException {
        Order order = getOrderWithValidData();
        order.setComplete(true);
        Order responseOrder = PetStoreAPI.createOrder(order, 400);
        assertEquals(responseOrder, null);
    }

    @Title("orderCreate_complete_fail_NoField")
    @Test(priority = 499)
    public void orderCreate_complete_fail_NoField() throws UnirestException, IOException {
        Order responseOrder = PetStoreAPI.createOrderWithNoFields(getOrder_Complete_DefaultValue(), 400, JsonInclude.Include.NON_DEFAULT);
        assertEquals(responseOrder, null);
    }

}