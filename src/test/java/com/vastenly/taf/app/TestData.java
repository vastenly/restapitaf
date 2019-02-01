package com.vastenly.taf.app;

import com.vastenly.taf.app.enums.OrderStatus;
import com.vastenly.taf.util.RandomUtils;

import static com.vastenly.taf.util.RandomUtils.*;


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

    public static Order getOrder_PetId_NoValue() {
        Order order = getOrderWithValidData();
        order.setPetId(null);
        return order;
    }

    public static Order getOrder_Quantity_DefaultValue() {
        Order order = getOrderWithValidData();
        order.setQuantity(0);
        order.setComplete(true);
        return order;
    }

    public static Order getOrder_ShipDate_NoValue() {
        Order order = getOrderWithValidData();
        order.setShipDate(null);
        return order;
    }

    public static Order getOrder_Status_NoValue() {
        Order order = getOrderWithValidData();
        order.setStatus(null);
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
        return "2019-10-26T17:06:56.745Z";
    }

    private static String outOfIntegerMax = "2147483648";

    public static String jsonString_quantity_outOfIntegerMax = " {\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"quantity\":" + outOfIntegerMax + ",\"shipDate\":\"2019-10-26T17:06:56.745Z\",\"status\":\"placed\",\"complete\":false\"";

    private static String outOfLongMax = "9223372036854775808";

    public static String jsonString_id_outOfLongMax = " {\"id\":" + outOfLongMax + ",\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"quantity\":" + getRandomInt(1, Integer.MAX_VALUE) + ",\"shipDate\":\"" + getShipDateValue() + "\",\"status\":\"placed\",\"complete\":false\"";

    public static String jsonString_petId_outOfLongMax = " {\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"petId\":" + outOfLongMax + ",\"quantity\":" + getRandomInt(1, Integer.MAX_VALUE) + ",\"shipDate\":\"" + getShipDateValue() + "\",\"status\":\"placed\",\"complete\":false\"";

    public static String jsonString_status_specialSymbol = " {\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"quantity\":" + getRandomInt(1, Integer.MAX_VALUE) + ",\"shipDate\":\"" + getShipDateValue() + "\",\"status\":\"" + getRandomStringBy(RandomUtils.RandomSymbolType.SPECIAL, 10) + "\",\"complete\":false\"";

    public static String jsonString_quantity_specialSymbol = " {\"id\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"petId\":" + getRandomLong(1L, Long.MAX_VALUE) + ",\"quantity\":" + getRandomStringBy(RandomUtils.RandomSymbolType.SPECIAL, 10) + ",\"shipDate\":\"" + getShipDateValue() + "\",\"status\":\"placed\",\"complete\":false\"";
}