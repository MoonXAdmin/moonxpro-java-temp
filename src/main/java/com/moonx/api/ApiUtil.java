package com.moonx.api;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.moonx.dto.ApiRequest;
import com.moonx.dto.ApiResponse;
import com.moonx.enums.ApiType;
import com.moonx.enums.RequestType;
import okhttp3.*;
import okhttp3.OkHttpClient.Builder;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.moonx.api.ApiSignature.generateSign;

public class ApiUtil {

    static final int CONN_TIMEOUT = 10;
    static final int READ_TIMEOUT = 60;
    static final int WRITE_TIMEOUT = 60;


    static final String API_URL = "https://exvillagedev.moonx.pro";

    static final MediaType JSON = MediaType.parse("application/json");


    static final OkHttpClient client = createOkHttpClient();

    public <T> ApiResponse<T> sendRequest(RequestType requestType, Object data, String businessNo, String key, Class<T> responseType) throws IOException {

        if (requestType.apiType == ApiType.PRIVATE){
            return post(requestType, data, businessNo, key, responseType);
        }

        else
            return null;
    }

    static  <T> ApiResponse<T> post(RequestType requestType, Object data, String businessNo, String key, Class<T> responseType) throws IOException {
        String nonceStr = RandomStringUtils.randomNumeric(32);
        int timestamp = (int) (System.currentTimeMillis() / 1000L);
        String sign;
        if (data != null) {
            JSONObject dataMap = (JSONObject) JSONObject.toJSON(data);
            sign = generateSign(dataMap, timestamp, nonceStr, key);
        } else {
            JSONObject jsonObject = new JSONObject();
            sign = generateSign(jsonObject, timestamp, nonceStr, key);
        }

        ApiRequest apiRequestDto = new ApiRequest();
        apiRequestDto.setData(data);
        apiRequestDto.setNonceStr(nonceStr);
        apiRequestDto.setTimestamp(timestamp);
        apiRequestDto.setSign(sign);
        apiRequestDto.setBusinessNo(businessNo);

        String jsonData = JSONObject.toJSONString(apiRequestDto, SerializerFeature.WriteMapNullValue,
                SerializerFeature.QuoteFieldNames, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteEnumUsingName);
        RequestBody body = RequestBody.create(JSON, jsonData);
        Request.Builder builder = new Request.Builder().url(API_URL + "/" + requestType.uri).post(body);
        Request request = builder.build();
        System.out.println("Request: "+ requestType);
        Response response = client.newCall(request).execute();
        ApiResponse resDto = JSONObject.parseObject(response.body().string(), ApiResponse.class);
        return resDto;
    }


    static OkHttpClient createOkHttpClient() {
        return new Builder().connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

}
