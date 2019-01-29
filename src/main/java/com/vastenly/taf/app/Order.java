package com.vastenly.taf.app;

import com.vastenly.taf.app.enums.OrderStatus;
import lombok.Data;

import static org.testng.Assert.assertEquals;


@Data
public class Order {

    public Order() {
    }

    public Long id;
    public Long petId;
    public int quantity;
    public String shipDate;
    public String status;
    public boolean complete;


    public Order(Long id, Long petId, int quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public Order(boolean withExampleData) {
        this.id = Long.valueOf(111);
        this.petId = Long.valueOf(1111);
        this.quantity = 1;
        this.shipDate = "2019-01-26T17:06:56.745Z";
        this.status = OrderStatus.PLACED.status();
        this.complete = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(getClass() == obj.getClass()))
            return false;
        else {
            Order tmp = (Order) obj;
            assertEquals(tmp.id, this.id);
            assertEquals(tmp.petId, this.petId);
            assertEquals(tmp.quantity, this.quantity);
            assertEquals(tmp.shipDate, this.shipDate);
            assertEquals(tmp.status, this.status);
            assertEquals(tmp.complete, this.complete);
            return true;
        }
    }

}