package com.lsw.androidutil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    private TextView textView;
    private ImageView imageView;
    private int currentTime = 60; // 设置初始倒计时时长为 60 秒
    private Handler handler = new Handler(Looper.getMainLooper());
    private int currentImageIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        initView();
        startCountdown(3);

    }

    private void initView() {
        textView = findViewById(R.id.text2);
        imageView = findViewById(R.id.imageView);
    }


    //使用handler实现倒计时
    private Runnable countdownRunnable = new Runnable() {
        @Override
        public void run() {
            // 倒计时逻辑
            textView.setText(String.valueOf(currentTime));
            currentTime--;
            if (currentTime >= 0) {
                handler.postDelayed(this, 1000);
            } else {
                // 倒计时结束的处理
                textView.setText("倒计时结束！！！");
                startAnimation();

            }
        }
    };
    private void startAnimation() {
        currentImageIndex = 0;
        handler.post(animationRunnable);
    }

    private void startCountdown(int initialTime) {
        currentTime = initialTime;
        handler.post(countdownRunnable);
    }



    private Runnable animationRunnable = new Runnable() {
        @Override
        public void run() {
            // 动画逻辑
            int imageResId = getResources().getIdentifier("image" + (currentImageIndex + 1), "drawable", getPackageName());
            imageView.setImageResource(imageResId);
            currentImageIndex++;
            if (currentImageIndex < 10) {
                handler.postDelayed(this, 300);
            } else {
                // 动画结束的处理
            }
        }
    };


}