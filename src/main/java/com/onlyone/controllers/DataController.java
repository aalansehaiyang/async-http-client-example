package com.onlyone.controllers;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author onlyone
 */
@RestController
@RequestMapping(value = "/data")
public class DataController {

    @RequestMapping(value = "/get1")
    public String get1(HttpServletRequest request, HttpServletResponse response) throws Exception {

        RequestBuilder builder = new RequestBuilder();
        builder.setUrl("http://www.kuaidi100.com/query");
        builder.addQueryParam("type", "yuantong");
        builder.addQueryParam("postid", "11111111111");

        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        String result = asyncHttpClient.executeRequest(builder.build(), new AsyncCompletionHandler<String>() {

            @Override
            public String onCompleted(Response response1) throws Exception {
                String _result = response1.getResponseBody();
                System.out.println(_result);

                // response.setContentType("text/json;charset=utf-8");
                // response.getWriter().print(_result);
                // response.getWriter().flush();

                // byte[] payload = _result.getBytes(StandardCharsets.UTF_8);
                // response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                // response.getHeaders().setContentLength(payload.length);
                // response.getBody().write(payload);
                // response.getBody().flush();

                return _result;
            }

            @Override
            public void onThrowable(Throwable t) {
                // Something wrong happened.
            }
        }).get(10, TimeUnit.SECONDS);

        asyncHttpClient.close();
        
        return result;

    }

}
