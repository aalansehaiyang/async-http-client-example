# 异步 httpclient
---

### 资料


* [异步的AsyncHttpClient使用详解](https://blog.csdn.net/angjunqiang/article/details/55259170)
* [java之httpClient 3.x、AsyncHttpClient1.9.x使用总结](https://www.cnblogs.com/xiaoMzjm/p/4564540.html)
* [AsyncHttpClient](https://github.com/scruffyfox/AsyncHttpClient)


### 代码案例

* com.onlyone.AsyncHttpClientTest#asyncHttpClient_prepareGet

提供最基本的prepareGet方法，并返回结果

* com.onlyone.AsyncHttpClientTest#asyncHttpClient_executeRequest_withAsyncHandler

执行request请求，并支持传入 org.asynchttpclient.AsyncHandler 自定义处理结果

* com.onlyone.AsyncHttpClientTest#asyncHttpClient_executeRequest_withAsyncHandler_v1

服务端有响应时即可立即读取，不用等到所有内容全响应过来。适用于数据量比较大的网络传输

* 