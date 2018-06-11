package com.onlyone;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Response;

/**
 * <pre>
 * 一些配置的设置
 * &#64;author onlyone
 *
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

    public static void main(String[] args) throws Exception {

        // 读超时设置为2ms
        DefaultAsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder().setReadTimeout(2).build();
        org.asynchttpclient.AsyncHttpClient cli = new DefaultAsyncHttpClient(config);
        cli.prepareGet("https://www.github.com/").execute(new MyCallback());

        // wait and quit
        Thread.sleep(1000);
        cli.close();
    }
}
