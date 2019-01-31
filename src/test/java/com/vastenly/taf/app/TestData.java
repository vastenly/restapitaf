package com.vastenly.taf.app;

import com.vastenly.taf.app.enums.OrderStatus;


/**
 * Data for REST api tests.
 */
public class TestData {

    public static Order getOrderWithValidData() {
        Order order = new Order();
        order.id = Long.valueOf(777 + (int) (Math.random() * 1000));
        order.petId = Long.valueOf(7777 + (int) (Math.random() * 1000));
        order.quantity = 1 + (int) (Math.random() * 10);
        order.shipDate = "2019-10-26T17:06:56.745Z";
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
}