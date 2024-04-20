# Android UI
## 1. RecyclerView的使用[参考《第一行代码》](https://github.com/guolindev/booksource/tree/master/chapter3/RecyclerViewTest)
1. 创建 RecyclerView 的适配器：创建一个适配器类，继承自 RecyclerView.Adapter，并实现必要的方法，如 onCreateViewHolder、onBindViewHolder 和 getItemCount。适配器负责将数据绑定到 RecyclerView 的每个项目上。

2. 创建 RecyclerView 的布局管理器：创建一个布局管理器类，用于确定 RecyclerView 中项目的排列方式，例如线性布局、网格布局等。你可以使用现有的布局管理器类，如 LinearLayoutManager 或 GridLayoutManager，或者自定义布局管理器。
![Screenshot_20240420_131331](https://github.com/shizishen/AnroidDemo/assets/85082613/3640397c-98dd-4040-82fa-f57c1aaa39b3)

## 2. ViewPager+Fragment实现底部导航栏
思路：BottomNavigationView实现底部导航栏，ViewPager+Fragment滑动页面
1. ViewPager中的滑动页面监听 接口onPageSelected()方法
2. BottomNavigationViewr中的底部图标切换监听 接口onNavigationItemSelected()方法
3. 滑动页面的时候改变底部导航栏图标id

![Screenshot_20240420_152731](https://github.com/shizishen/AnroidDemo/assets/85082613/a31ca04c-cd2c-4324-a95c-d9ca2a26d3ad)

## 3.webview+Html实现本地html网页
1. 关键步骤：
```
WebSettings webSettings = mWebView.getSettings();
webSettings.setJavaScriptEnabled(true);
mWebView.loadUrl("file:///android_asset/discovery.html");
```
![Screenshot_20240420_165137](https://github.com/shizishen/AnroidDemo/assets/85082613/46b038d2-edb1-4ee0-af9d-bf1f1b08757e)



