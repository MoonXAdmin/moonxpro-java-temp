package com.moonx.dto;

public class BulkCancelRequest {
    private String exchangeOrderIds;
    private String symbol;

    public String getExchangeOrderIds() {
        return exchangeOrderIds;
    }

    public void setExchangeOrderIds(String exchangeOrderIds) {
        this.exchangeOrderIds = exchangeOrderIds;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
