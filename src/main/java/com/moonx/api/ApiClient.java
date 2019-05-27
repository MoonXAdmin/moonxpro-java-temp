package com.moonx.api;

import com.moonx.dto.*;
import com.moonx.enums.RequestType;

import java.io.IOException;

public class ApiClient {

    final String businessNo;
    final String apiSecret;

    public ApiClient(String businessNo, String apiSecret){
        this.businessNo = businessNo;
        this.apiSecret = apiSecret;
    }

    public ApiResponse<String> createNewOrder(NewOrderRequest newOrderRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.NEW_ORDER, newOrderRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> assetQuery(AssetQueryRequest assetQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.ASSET_QUERY, assetQueryRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> cancelOrder(CancelOrderRequest cancelOrderRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.CANCEL_ORDER, cancelOrderRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> bulkCancel(BulkCancelRequest bulkCancelReq) throws IOException {
        return new ApiUtil().sendRequest(RequestType.CANCEL_BULK, bulkCancelReq, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> orderQuery(OrderQueryRequest orderQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.ORDER_QUERY, orderQueryRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> queryAllOpen(OrderQueryRequest orderQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.ALL_OPEN_ORDERS_QUERY, orderQueryRequest, this.businessNo, this.apiSecret, String.class);
    }

    public ApiResponse<String> symbols(SymbolQueryRequest symbolQueryRequest) throws IOException {
        return new ApiUtil().sendRequest(RequestType.SYMBOLS, symbolQueryRequest, this.businessNo, this.apiSecret, String.class);
    }
}
