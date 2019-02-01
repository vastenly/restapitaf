package com.vastenly.taf.app;

import com.vastenly.taf.app.enums.OrderStatus;


/**
 * Data for REST api tests.
 */
public class TestData {

    public static Order getOrderWithValidData() {
        Order order = new Order();
        order.id = getRandomIdValue();
        order.petId = getRandomPetIdValue();
        order.quantity = getRandomQuantityValue();
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
        Order order = new Order();
        return order;
    }

    private static Long getRandomIdValue() {
        return Long.valueOf(777 + (int) (Math.random() * 1000));
    }

    private static Long getRandomPetIdValue() {
        return Long.valueOf(7777 + (int) (Math.random() * 1000));
    }

    private static int getRandomQuantityValue() {
        return 1 + (int) (Math.random() * 10);
    }

    private static String getShipDateValue() {
        return "2019-10-26T17:06:56.745Z";
    }

    private static String outOfIntegerMax = "2147483648";

    public static String jsonString_quantity_outOfIntegerMax = " {\"id\":" + getRandomIdValue() + ",\"petId\":" + getRandomPetIdValue() + ",\"quantity\":" + outOfIntegerMax + ",\"shipDate\":\"2019-10-26T17:06:56.745Z\",\"status\":\"placed\",\"complete\":false\"";

    private static String outOfLongMax = "9223372036854775808";

    public static String jsonString_id_outOfLongMax = " {\"id\":" + outOfLongMax + ",\"petId\":" + getRandomPetIdValue() + ",\"quantity\":" + getRandomQuantityValue() + ",\"shipDate\":\"" + getShipDateValue() + "\",\"status\":\"placed\",\"complete\":false\"";

    public static String jsonString_petId_outOfLongMax = " {\"id\":" + getRandomIdValue() + ",\"petId\":" + outOfLongMax + ",\"quantity\":" + getRandomQuantityValue() + ",\"shipDate\":\"" + getShipDateValue() + "\",\"status\":\"placed\",\"complete\":false\"";

}