package com.lsw.androidutil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import java.lang.ref.WeakReference;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private Button bt_handler_send_child;
    private Button bt_handler_time;
    private TextView textView;
    private static final Logger logger = Logger.getLogger(MainActivity.class.getName());


    //定义静态内部类
    public static class MyHandler extends Handler{
        //弱引用持有MainActivity ,GC时会被回收，防止 Activity 会被 Handler 持有而无法释放
        private WeakReference<MainActivity> weakReference;
        public MyHandler(MainActivity activity){
            this.weakReference = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            MainActivity activity = weakReference.get();
            super.handleMessage(msg);
            if (null != activity){
                switch (msg.what){
                    case 0:
                        //执行业务逻辑
                        activity.textView.setText("在子线程向主线程发送信息，修改UI");
                        break;
                    case 1:
                        //执行业务逻辑
                        break;
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initView();

        //创建Handler
        final MyHandler handler = new MyHandler(this);

        bt_handler_send_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //使用handler发送空消息
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });

        bt_handler_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }



    private void initView() {
        textView = findViewById(R.id.textView);
        bt_handler_send_child = findViewById(R.id.bt_handler_send);
        bt_handler_time = findViewById(R.id.handler_time);

    }

    @Override
    protected void onDestroy() {
        //移除所有回调以及消息
        //myHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}