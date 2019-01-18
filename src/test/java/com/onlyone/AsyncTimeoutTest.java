package com.onlyone;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Response;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * <pre>
 * 一些配置的设置
 * &#64;author onlyone
 * </pre>
 */
public class AsyncTimeoutTest {

    static class MyCallback extends AsyncCompletionHandler<Boolean> {

        @Override
        public Boolean onCompleted(Response response) throws Exception {
            System.out.println(response.getResponseBody());
            return true;
        }

        public void onThrowable(Throwable t) {
            t.printStackTrace(System.err);
        }
    }

    @Test
    public void asyncHttpClient_prepareGet() throws InterruptedException, ExecutionException, IOException {

        // 读超时设置为2ms
        DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setReadTimeout(20).build();
        org.asynchttpclient.AsyncHttpClient cli = new DefaultAsyncHttpClient(config);
        cli.prepareGet("http://www.kuaidi100.com/query?type=yuantong&postid=11111111111").execute(new MyCallback());

        // wait and quit
        Thread.sleep(1000);
        cli.close();
    }
}
