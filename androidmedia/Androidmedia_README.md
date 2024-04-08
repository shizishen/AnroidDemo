# Android Media
MediaPlayer的使用，Camera使用

## 1. MediaPlayer

- 创建MediaPlayer实例
- 设置播放文件
- MediaPlayer的其他方法使用
```
//创建实例
MediaPlayer mp = new MediaPlayer();
//MediaPlayer mp = MediaPlayer.create(this, R.raw.test);  //无需再调用setDataSource
//raw下的资源：
//MediaPlayer.create(this, R.raw.test);

//本地文件路径：
//mp.setDataSource("/sdcard/test.mp3");

//网络URL文件：
mp.setDataSource("http://www.xxx.com/music/test.mp3");


/**
getCurrentPosition( )：得到当前的播放位置
getDuration() ：得到文件的时间
getVideoHeight() ：得到视频高度
getVideoWidth() ：得到视频宽度
isLooping()：是否循环播放
isPlaying()：是否正在播放
pause()：暂停
prepare()：准备(同步)
prepareAsync()：准备(异步)
release()：释放MediaPlayer对象
reset()：重置MediaPlayer对象
seekTo(int msec)：指定播放的位置（以毫秒为单位的时间）
setAudioStreamType(int streamtype)：指定流媒体的类型
setDisplay(SurfaceHolder sh)：设置用SurfaceHolder来显示多媒体
setLooping(boolean looping)：设置是否循环播放
setOnBufferingUpdateListener(MediaPlayer.OnBufferingUpdateListener listener)： 网络流媒体的缓冲监听
setOnCompletionListener(MediaPlayer.OnCompletionListener listener)： 网络流媒体播放结束监听
setOnErrorListener(MediaPlayer.OnErrorListener listener)： 设置错误信息监听
setOnVideoSizeChangedListener(MediaPlayer.OnVideoSizeChangedListener listener)： 视频尺寸监听
setScreenOnWhilePlaying(boolean screenOn)：设置是否使用SurfaceHolder显示
setVolume(float leftVolume, float rightVolume)：设置音量
start()：开始播放
stop()：停止播放
*/
```

## Camera