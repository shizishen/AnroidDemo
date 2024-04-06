package com.lsw.androidnet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class MainActivity extends AppCompatActivity {
    private TextView txtMenu,txtShow;
    private ImageView imgPic;
    private WebView webView;
    private ScrollView scroll;
    private Bitmap bitmap;
    private String detail = "";
    private Boolean flag = false;
    private final static String PIC_URL = "https://images.pexels.com/photos/16039120/pexels-photo-16039120.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load";
    private final static String HTML_URL =  "https://www.baidu.com/";

    //用于刷新界面
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what){case 0x001:
                hideAllWidget();
                imgPic.setVisibility(View.VISIBLE);
                imgPic.setImageBitmap(bitmap);
                Toast.makeText(MainActivity.this, "图片加载完毕", Toast.LENGTH_SHORT).show();
                break;
                case 0x002:
                    hideAllWidget();
                    scroll.setVisibility(View.VISIBLE);
                    txtShow.setText(detail);
                    Toast.makeText(MainActivity.this, "HTML代码加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                case 0x003:
                    hideAllWidget();
                    webView.setVisibility(View.VISIBLE);
                    webView.loadDataWithBaseURL("", detail, "text/html", "UTF-8", "");
                    Toast.makeText(MainActivity.this, "网页加载完毕", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void initView(){
        txtMenu = findViewById(R.id.txtMenu);
        txtShow = findViewById(R.id.txtshow);
        imgPic = findViewById(R.id.imgPic);
        webView = findViewById(R.id.webView);
        scroll = findViewById(R.id.scroll);
        //用于给指定的视图对象（txtMenu）注册上下文菜单，以便在用户长按该视图时显示相应的菜单选项
        registerForContextMenu(txtMenu);
    }

    //定义一个隐藏所有控件的方法
    private void hideAllWidget(){
        //View.GONE表示视图不可见，并且不占据空间，可能会影响其他布局
        imgPic.setVisibility(View.GONE);
        scroll.setVisibility(View.GONE);
        webView.setVisibility(View.GONE);
    }

    //重写上下文菜单的创建方法



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflator = new MenuInflater(this);
        inflator.inflate(R.menu.menus, menu);
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.one){
            new Thread(){
                @Override
                public void run() {
                    try {
                        byte[] data = GetData.getImage(PIC_URL);
                        bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0x001);
                }
            }.start();
        }else if (item.getItemId() == R.id.two){
            new Thread(){
                @Override
                public void run() {
                    try {
                        detail = GetData.getHtml(HTML_URL);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0x002);
                }
            }.start();

        } else if (item.getItemId() == R.id.three) {
            if (detail.equals("")){
                Toast.makeText(MainActivity.this,"请先请求HTML",Toast.LENGTH_SHORT).show();
            }else {
                handler.sendEmptyMessage(0x003);
            }
        }
        return super.onContextItemSelected(item);
    }
}