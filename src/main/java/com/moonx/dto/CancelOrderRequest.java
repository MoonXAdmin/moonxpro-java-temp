package com.moonx.dto;

public class CancelOrderRequest {
    long exchangeOrderId;

    public long getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(long exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }
}
