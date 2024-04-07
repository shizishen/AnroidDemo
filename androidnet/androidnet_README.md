# Android NET

## 1. Httpurlconnection的使用
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
conn.setConnectTimeout(6*1000);**conn.setReadTimeout(6 * 1000);
InputStream in = conn.getInputStream();
conn.disconnect();
```

## 结果展示：

<div style="display: flex;">
  <img src="https://github.com/shizishen/AnroidDemo/assets/85082613/f32bed33-366d-4d0d-9301-1f0d08fe991a" alt="加载图片" style="width: 30%;">
  <img src="https://github.com/shizishen/AnroidDemo/assets/85082613/20072515-1e26-4064-aa89-c6993abfd38b" alt="加载HTML源码" style="width: 30%;">
  <img src="https://github.com/shizishen/AnroidDemo/assets/85082613/2106bb23-2266-4b9b-a245-5e67ddfe8416" alt="加载网页" style="width: 30%;">
</div>


## 2.Okhttp的使用



感谢一下高质量教程
[Android基础入门教程](https://www.kancloud.cn/kancloud/android-tutorial)