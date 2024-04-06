package com.lsw.androidnet;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

//这个类的作用是将流转换成二进制数组
public class StreamTool {
    //从数据流中读取数据，byte[]是一个字节数组类型，
    // 每个元素都是一个8位字节（即1个字节）的值。
    // static修饰可以直接通过类名访问，不需要实例化
    public static byte[] read(InputStream inStream) throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1){
            outputStream.write(buffer,0,len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }
}
