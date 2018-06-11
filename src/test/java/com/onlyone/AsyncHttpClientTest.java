package com.onlyone;

import io.netty.handler.codec.http.HttpHeaders;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.asynchttpclient.*;
import org.junit.Test;

/**
 * @author onlyone
 */
public class AsyncHttpClientTest {

    @Test
    public void asyncHttpClient_prepareGet() throws InterruptedException, ExecutionException {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
        Future<Response> f = asyncHttpClient.prepareGet("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111").execute();
        Response r = f.get();
        System.out.println(r.getResponseBody());
    }

    @Test
    public void asyncHttpClient_executeRequest_withAsyncHandler() throws InterruptedException, ExecutionException {

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

    /**
     * 服务端有响应时即可立即读取，不用等到所有内容全响应过来。适用于数据量比较大的网络传输
     */
    @Test
    public void asyncHttpClient_executeRequest_withAsyncHandler_v1() throws InterruptedException, ExecutionException {
        AsyncHttpClient c = new DefaultAsyncHttpClient();
        Future<String> f = c.prepareGet("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111").execute(new AsyncHandler<String>() {

            private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            @Override
            public void onThrowable(Throwable t) {

            }

            @Override
            public State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                bytes.write(bodyPart.getBodyPartBytes());
                return State.CONTINUE;
            }

            @Override
            public State onStatusReceived(HttpResponseStatus responseStatus) throws Exception {
                int statusCode = responseStatus.getStatusCode();
                System.out.println("response状态码：" + statusCode);
                if (statusCode >= 500) {
                    return State.ABORT;
                }
                return State.CONTINUE;
            }

            @Override
            public State onHeadersReceived(HttpResponseHeaders headers) throws Exception {
                HttpHeaders header = headers.getHeaders();
                return State.CONTINUE;
            }

            @Override
            public String onCompleted() throws Exception {
                return bytes.toString("UTF-8");
            }

        });

        String bodyResponse = f.get();
        System.out.println("响应结果：" + bodyResponse);
    }

}
