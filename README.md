# 异步 httpclient
---

## 一、 apache版


#### jar包依赖：

```
 <dependency>
     <groupId>org.apache.httpcomponents</groupId>
     <artifactId>httpasyncclient</artifactId>
     <version>4.1.1</version>
</dependency>
```

* [异步的AsyncHttpClient使用详解](https://blog.csdn.net/angjunqiang/article/details/55259170)
* 

## 二、AsyncHttpClient版（推荐）

`从pom依赖可以发现，底层依赖于Netty的NIO异步事件通知模式来实现非阻塞！`

#### 资料：

* [源码](https://github.com/AsyncHttpClient/async-http-client)

* [再谈AsyncHttpClient](http://hongjiang.info/asynchttpclient-v2-0-readtimeout-bug/)

* [java之httpClient 3.x、AsyncHttpClient1.9.x使用总结](https://www.cnblogs.com/xiaoMzjm/p/4564540.html)

#### jar包依赖：

```
<dependency>
	<groupId>org.asynchttpclient</groupId>
	<artifactId>async-http-client</artifactId>
	<version>2.0.32</version>
</dependency>
```

* com.onlyone.AsyncHttpClientTest#asyncHttpClient_prepareGet

提供最基本的prepareGet方法，并返回结果

* com.onlyone.AsyncHttpClientTest#asyncHttpClient_executeRequest_withAsyncHandler

执行request请求，并支持传入 org.asynchttpclient.AsyncHandler 自定义处理结果

* com.onlyone.AsyncHttpClientTest#asyncHttpClient_executeRequest_withAsyncHandler_v1

服务端有响应时即可立即读取，不用等到所有内容全响应过来。适用于数据量比较大的网络传输

* com.onlyone.AsyncTimeoutTest#asyncHttpClient_prepareGet

超时测试



## 三、okhttp

#### 资料：

* [源码](https://github.com/square/okhttp)
* [基本使用——OkHttp3详细使用教程](https://blog.csdn.net/xx326664162/article/details/77714126)
* [Okhttp3基本使用](https://www.jianshu.com/p/da4a806e599b)
* [AsyncHttpClient](https://github.com/scruffyfox/AsyncHttpClient)


#### jar 包依赖：

```
<dependency>
  <groupId>com.squareup.okhttp3</groupId>
  <artifactId>okhttp</artifactId>
  <version>3.11.0</version>
</dependency>
```