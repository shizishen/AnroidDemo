package com.lsw.androidnet;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetData {
    //定义一个获取图片数据的方法
    public static byte[] getImage(String path)throws Exception{
        //创建URL对象
        URL url = new URL(path);
        //使用url.openConnection()创建HttpURLConnection对象
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //配置HttpURLConnection对象的参数
        conn.setConnectTimeout(5000); //设置连接超时为5秒
        conn.setRequestMethod("GET"); //设置请求类型为GET类型
        //判断请求是否成功
        if(conn.getResponseCode() != 200){
            throw new RuntimeException("请求url失败");
        }
        InputStream inStream = conn.getInputStream();
        byte[] bt = StreamTool.read(inStream);
        inStream.close();
        return bt;
    }

    //获取网页的html源码
    public static String getHtml(String path)throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == 200){
            InputStream in = conn.getInputStream();
            byte[] data = StreamTool.read(in);
            String html = new String(data,"UTF-8");
            return html;
        }
        return null;
    }
}
