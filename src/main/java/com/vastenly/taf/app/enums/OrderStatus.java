package com.vastenly.taf.app.enums;

public enum OrderStatus {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }

}

