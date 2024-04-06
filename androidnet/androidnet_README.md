# 1.HttpURLConnection的使用
HttpURLConnection是Java提供的用于发送HTTP请求和接收HTTP响应的类。它是URLConnection的子类，用于建立与服务器的HTTP连接，并提供了许多方法来设置请求属性、发送请求、读取响应和获取响应信息。

- 创建一个URL对象： 
- 调用URL对象的openConnection( )来获取HttpURLConnection对象实例：
- 设置HTTP请求使用的方法:GET或者POST，或者其他请求方式比如：PUT 
- 设置连接超时，读取超时的毫秒数，以及服务器希望得到的一些消息头 
- 调用getInputStream()方法获得服务器返回的输入流，然后输入流进行读取了 
- 最后调用disconnect()方法将HTTP连接关掉 
```
  URL url = new URL(http://www.baidu.com);
  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
  conn.setRequestMethod("GET");
  conn.setConnectTimeout(6*1000);
  conn.setReadTimeout(6 * 1000);
  InputStream in = conn.getInputStream();
  conn.disconnect(); 
```

  
 参考：
 [Android基础入门教程](https://www.kancloud.cn/kancloud/android-tutorial/87287)