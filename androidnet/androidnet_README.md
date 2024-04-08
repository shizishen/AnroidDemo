# Android NET
Android网络开发：Httpurlconnection，OKhttp，Socket

## 1. Httpurlconnection的使用(默认GET方式)
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

## 结果展示：

<div style="display: flex;">
  <img src="https://github.com/shizishen/AnroidDemo/assets/85082613/f32bed33-366d-4d0d-9301-1f0d08fe991a" alt="加载图片" style="width: 30%;">
  <img src="https://github.com/shizishen/AnroidDemo/assets/85082613/20072515-1e26-4064-aa89-c6993abfd38b" alt="加载HTML源码" style="width: 30%;">
  <img src="https://github.com/shizishen/AnroidDemo/assets/85082613/2106bb23-2266-4b9b-a245-5e67ddfe8416" alt="加载网页" style="width: 30%;">
</div>


## 2.Okhttp的使用

## 3.Socket的使用
![image](https://github.com/shizishen/AnroidDemo/assets/85082613/1f4e09c7-636a-42cc-b1d1-ed32157f38f5)
### 客户端建立socket通信步骤（注意！！！ Android端不能将网络操作放在主线程）：
- 创建socket对象，指明需要链接的服务器的地址和端口号
- 链接创建后，通过输出流向服务器的地址和端口号发送请求信息
- 通过输出流获得服务器响应的信息
- 关闭相关资源
```
//1.创建客户端Socket，指定服务器地址和端口
Socket socket = new Socket("172.16.2.54", 12345);
//2.获取输出流，向服务器端发送信息
OutputStream os = socket.getOutputStream();//字节输出流
PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
//获取客户端的IP地址
InetAddress address = InetAddress.getLocalHost();
String ip = address.getHostAddress();
pw.write("客户端：~" + ip + "~ 接入服务器！！");
pw.flush();
socket.shutdownOutput();//关闭输出流
socket.close();
```
### 服务器端建立socket通信步骤：
- 创建ServerSocket对象，绑定监听的端口
- 调用accept()方法监听客户端的请求
- 连接建立后，通过输入流读取客户端发送请求信息
- 通过输出流向客户端发送响应信息
- 关闭资源
```
//1.创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
ServerSocket serverSocket = new ServerSocket(12345);
InetAddress address = InetAddress.getLocalHost();
String ip = address.getHostAddress();
Socket socket = null;
//2.调用accept()等待客户端连接
System.out.println("~~~服务端已就绪，等待客户端接入~，服务端ip地址: " + ip);
socket = serverSocket.accept();
//3.连接后获取输入流，读取客户端信息
InputStream is=null;
InputStreamReader isr=null;
BufferedReader br=null;
OutputStream os=null;
PrintWriter pw=null;
is = socket.getInputStream();     //获取输入流
isr = new InputStreamReader(is,"UTF-8");
br = new BufferedReader(isr);
String info = null;
while((info=br.readLine())!=null){//循环读取客户端的信息
	System.out.println("客户端发送过来的信息" + info);
}
socket.shutdownInput();//关闭输入流
socket.close();
```

### 基于UDP协议的Socket通信
#### 客户端实现
- 定义发送信息
- 创建DatagramPacket，包含将要发送的信息
- 创建DatagramSocket
- 发送数据
```
// 1.定义服务器的地址、端口号、数据
InetAddress address = InetAddress.getByName("localhost");
int port = 8800;
byte[] data = "用户名：admin;密码：123".getBytes();
// 2.创建数据报，包含发送的数据信息
DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
// 3.创建DatagramSocket对象
DatagramSocket socket = new DatagramSocket();
// 4.向服务器端发送数据报
socket.send(packet);
// 1.创建数据报，用于接收服务器端响应的数据
 byte[] data2 = new byte[1024];
DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
// 2.接收服务器响应的数据
socket.receive(packet2);
// 3.读取数据
String reply = new String(data2, 0, packet2.getLength());
System.out.println("我是客户端，服务器说：" + reply);
// 4.关闭资源
socket.close();
```
  








#### 服务器端
- 创建DatagramSocket，指定端口号
- 创建DatagramPacket
- 接收客户端发送的数据信息
- 读取数据

```
// 1.创建服务器端DatagramSocket，指定端口
DatagramSocket socket = new DatagramSocket(12345);
// 2.创建数据报，用于接收客户端发送的数据
byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
DatagramPacket packet = new DatagramPacket(data, data.length);
// 3.接收客户端发送的数据
System.out.println("****服务器端已经启动，等待客户端发送数据");
socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
// 4.读取数据
String info = new String(data, 0, packet.getLength());
System.out.println("我是服务器，客户端说：" + info);
// 1.定义客户端的地址、端口号、数据
InetAddress address = packet.getAddress();
 int port = packet.getPort();
yte[] data2 = "欢迎您!".getBytes();
// 2.创建数据报，包含响应的数据信息
DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
// 3.响应客户端
socket.send(packet2);
// 4.关闭资源
socket.close();
```

感谢以下高质量教程
[Android基础入门教程](https://www.kancloud.cn/kancloud/android-tutorial)
