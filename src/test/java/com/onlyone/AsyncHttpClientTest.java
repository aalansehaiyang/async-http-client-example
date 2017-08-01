package com.onlyone;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.RequestBuilder;
import org.asynchttpclient.Response;
import org.junit.Test;

/**
 * @author onlyone
 */
public class AsyncHttpClientTest {

    @Test
    public void testAsyncWithQueryParam_1() throws InterruptedException, ExecutionException {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        Future<Response> f = asyncHttpClient.prepareGet("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111").execute();
        Response r = f.get();
        System.out.println(r.getResponseBody());
    }

    @Test
    public void testAsyncWithQueryParam_2() throws InterruptedException, ExecutionException {

        RequestBuilder builder = new RequestBuilder();
        builder.setUrl("http://www.kuaidi100.com/query");
        builder.addQueryParam("type", "yuantong");
        builder.addQueryParam("postid", "11111111111");

        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();

        String result = asyncHttpClient.executeRequest(builder.build(), new AsyncCompletionHandler<String>() {

            @Override
            public String onCompleted(Response response) throws Exception {
                String _result = response.getResponseBody();
                System.out.println("result in onCompleted= " + _result);
                return _result;
            }

            @Override
            public void onThrowable(Throwable t) {
                // Something wrong happened.
            }
        }).get();

        System.out.println("最新的结果：" + result);
    }
}
