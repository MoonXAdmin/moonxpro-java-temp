package com.moonx.api;

import com.alibaba.fastjson.JSONObject;
import com.moonx.dto.*;
import com.moonx.enums.TradeCoinFlag;
import com.moonx.enums.TradeCoinType;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;

public class Main {
    static final String BUSINESS_NO = "BUSINESS_NO";
    static final String API_SECRET = "API_SECRET";

    public static void main(String[] args) throws IOException {

        apiTest();
    }

    static void apiTest() throws IOException {
        ApiClient client = new ApiClient(BUSINESS_NO, API_SECRET);


        /**************************** Symbol Request *******************************/
        SymbolQueryRequest symbolQuery = new SymbolQueryRequest();
        symbolQuery.setSymbol("BTC_ETH"); //Optional. List of all available symbols will be returned if not provided or if invalid value is passed
        //printResponse(client.symbols(symbolQuery));


        /**************************Post Asset Query Request************************/
        AssetQueryRequest assetQuery = new AssetQueryRequest();
        assetQuery.setAssetCode("BTC");
        //printResponse(client.assetQuery(assetQuery));


        /************************* Post New Order Request ************************/
        NewOrderRequest order = new NewOrderRequest();
        order.setSymbol("BTC_ETH");
        order.setAmount("0.125");
        order.setPrice("0.03212"); // will be ignored if TradeCoinFlag is MARKET.
        order.setTradeCoinFlag(TradeCoinFlag.FIXED);
        order.setTradeCoinType(TradeCoinType.BUY);
        //printResponse(client.createNewOrder(order));


        /************************ Post Order Query Request*************************/
        OrderQueryRequest orderQuery = new OrderQueryRequest();
        orderQuery.setExchangeOrderId(42097);
        //printResponse(client.orderQuery(orderQuery));


        /************************ Post Open Order Query Request************************/
        OrderQueryRequest allOpenOrders = new OrderQueryRequest();
        allOpenOrders.setSymbol("BTC_ETH");
        //printResponse(client.queryAllOpen(allOpenOrders));



        /************************* Post Cancel Order Request **************************/
        CancelOrderRequest cancelOrder = new CancelOrderRequest();
        cancelOrder.setExchangeOrderId(1);
        //printResponse(client.cancelOrder(cancelOrder));


        /************************ Post Bulk Cancel Request*****************************/
        BulkCancelRequest bulkCancelRequest = new BulkCancelRequest();
        bulkCancelRequest.setSymbol("BTC_ETH"); // will be ignored if exchangeOrderIds are provided
        bulkCancelRequest.setExchangeOrderIds("0,1,2");
        //printResponse(client.bulkCancel(bulkCancelRequest));


    }



    static void printResponse(Object obj) {
        System.out.println("Response: ");
        System.out.println(StringEscapeUtils.
                unescapeJava(JSONObject.toJSONString(obj, true)));
        System.out.println();
    }
}
