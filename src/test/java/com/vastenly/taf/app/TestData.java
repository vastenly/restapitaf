package com.vastenly.taf.app;

import com.vastenly.taf.app.enums.OrderStatus;
import com.vastenly.taf.util.RandomUtils.RandomSymbolType;

import static com.vastenly.taf.app.AppConstants.SPACE_CHAR;
import static com.vastenly.taf.util.RandomUtils.*;
import static com.vastenly.taf.util.TestDateGenerator.generateCurrentDate;
import static com.vastenly.taf.util.TestDateGenerator.incrementInputDate;


/**
 * Data for REST api tests.
 */
public class TestData {

    public static Order getOrderWithValidData() {
        Order order = new Order();
        order.id = getRandomLong(1L, Long.MAX_VALUE);
        order.petId = getRandomLong(1L, Long.MAX_VALUE);
        order.quantity = getRandomInt(1, Integer.MAX_VALUE);
        order.shipDate = getShipDateValue();
        order.status = OrderStatus.PLACED.status();
        order.complete = false;
        return order;
    }

    public static Order getOrder_Id_NoValue() {
        Order order = getOrderWithValidData();
        order.setId(null);
        return order;
    }

    public static Order getOrder_Id_NegativeNumber() {
        Order order = getOrderWithValidData();
        order.setId(getRandomLong(Long.MIN_VALUE, -1L));
        return order;
    }

    public static Order getOrder_PetId_NoValue() {
        Order order = getOrderWithValidData();
        order.setPetId(null);
        return order;
    }

    public static Order getOrder_PetId_NegativeNumber() {
        Order order = getOrderWithValidData();
        order.setPetId(getRandomLong(Long.MIN_VALUE, -1L));
        return order;
    }

    public static Order getOrder_Quantity_DefaultValue() {
        Order order = getOrderWithValidData();
        order.setQuantity(0);
        order.setComplete(true);
        return order;
    }

    public static Order getOrder_Quantity_NegativeNumber() {
        Order order = getOrderWithValidData();
        order.setQuantity(getRandomInt(Integer.MIN_VALUE, -1));
        return order;
    }

    public static Order getOrder_ShipDate_DaysToIncrement(int daysToIncrement) {
        Order order = getOrderWithValidData();
        order.setShipDate(getShipDateValue(daysToIncrement));
        return order;
    }

    public static Order getOrder_ShipDate_NoValue() {
        Order order = getOrderWithValidData();
        order.setShipDate(null);
        return order;
    }

    public static Order getOrder_ShipDate_SpaceValue() {
        Order order = getOrderWithValidData();
        order.setShipDate(SPACE_CHAR);
        return order;
    }

    public static Order getOrder_Status_NoValue() {
        Order order = getOrderWithValidData();
        order.setStatus(null);
        return order;
    }

    public static Order getOrder_Status_SpaceValue() {
        Order order = getOrderWithValidData();
        order.setStatus(SPACE_CHAR);
        return order;
    }

    public static Order getOrder_Complete_DefaultValue() {
        Order order = getOrderWithValidData();
        order.setComplete(false);
        return order;
    }

    public static Order getOrderWithNoFields() {
        return new Order();
    }

    private static String getShipDateValue() {
        return generateCurrentDate(AppConstants.APPLICATION_DATE_TEMPLATE);
    }

    private static String getShipDateValue(int daysToIncrement) {
        return incrementInputDate(getShipDateValue(), AppConstants.APPLICATION_DATE_TEMPLATE, daysToIncrement);
    }

    private static String outOfLongMax = "9223372036854775808";
    private static String outOfIntegerMax = "2147483648";

    public static String jsonString_id_outOfLongMax = " {" +
            "\"id\":" + outOfLongMax + "," +
            "\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"quantity\":" + getRandomInt(1, Integer.MAX_VALUE) + "," +
            "\"shipDate\":\"" + getShipDateValue() + "\"," +
            "\"status\":\"" + OrderStatus.PLACED.status() + "\"," +
            "\"complete\":false\"";

    public static String jsonString_petId_outOfLongMax = " {" +
            "\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"petId\":" + outOfLongMax + "," +
            "\"quantity\":" + getRandomInt(1, Integer.MAX_VALUE) + "," +
            "\"shipDate\":\"" + getShipDateValue() + "\"," +
            "\"status\":\"" + OrderStatus.PLACED.status() + "\"," +
            "\"complete\":false\"";

    public static String jsonString_quantity_outOfIntegerMax = " {" +
            "\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"quantity\":" + outOfIntegerMax + "," +
            "\"shipDate\":\"" + getShipDateValue() + "\"," +
            "\"status\":\"" + OrderStatus.PLACED.status() + "\"," +
            "\"complete\":false\"";

    public static String jsonString_quantity_specialSymbol = " {" +
            "\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"quantity\":" + getRandomStringBy(RandomSymbolType.SPECIAL, 10) + "," +
            "\"shipDate\":\"" + getShipDateValue() + "\"," +
            "\"status\":\"" + OrderStatus.PLACED.status() + "\"," +
            "\"complete\":false\"";

    public static String jsonString_shipDate_specialSymbol = " {" +
            "\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"quantity\":" + getRandomInt(1, Integer.MAX_VALUE) + "," +
            "\"shipDate\":\"" + getRandomStringBy(RandomSymbolType.SPECIAL, 10) + "\"," +
            "\"status\":\"" + OrderStatus.PLACED.status() + "\"," +
            "\"complete\":false\"";

    public static String jsonString_status_specialSymbol = " {" +
            "\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + "," +
            "\"quantity\":" + getRandomInt(1, Integer.MAX_VALUE) + "," +
            "\"shipDate\":\"" + getShipDateValue() + "\"," +
            "\"status\":\"" + getRandomStringBy(RandomSymbolType.SPECIAL, 10) + "\"," +
            "\"complete\":false\"";
}
