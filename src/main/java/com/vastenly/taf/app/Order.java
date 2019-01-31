package com.vastenly.taf.app;

import com.vastenly.taf.util.StringUtils;
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
            this.shipDate = StringUtils.replaceLast(this.shipDate, "Z", "+0000");
            assertEquals(tmp.shipDate, this.shipDate);
            assertEquals(tmp.status, this.status);
            assertEquals(tmp.complete, this.complete);
            return true;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean getComplete() {
        return complete;
    }
}