package com.example.myapplication;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.File;

import io.reactivex.rxjava3.functions.Consumer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

public class MainActivity extends AppCompatActivity {
    IjkplayerVideoView_TextureView ijkTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ijkTextureView = findViewById(R.id.ijkTextureView);
        ijkTextureView.setListener(new IjkplayerVideoView_TextureView.VideoPlayerListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                Log.i("tag", "onBufferingUpdate");
            }

            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.i("tag", "onError");
                return false;
            }

            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.i("tag", "onInfo");
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                Log.i("tag", "onPrepared");
                ijkTextureView.getmMediaPlayer().start();

            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.i("tag", "onSeekComplete");
            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.i("tag", "onCompletion");
            }
        });

        new RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        if (aBoolean) {
                            File externalStorageDirectory = Environment.getExternalStorageDirectory();
                            Log.i("tag", "额外存储路径: " + externalStorageDirectory.getAbsolutePath());
                            Toast.makeText(MainActivity.this, "权限被授予了,开始播放", Toast.LENGTH_SHORT).show();
                            ijkTextureView.setVideoPath("storage/emulated/0/Android/data/com.example.myapplication/Z_CWMJ/云天励飞智能测温门禁.mp4");

//                            ijkTextureView.setVideoPath("rtsp://admin:admin@192.168.18.21:554/media/live/1/1");
//                            ijkTextureView.setVideoPath("rtsp://admin:admin@192.168.18.7:554/media/live/1/1");
                        } else {
                            Toast.makeText(MainActivity.this, "权限被拒绝了", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//
    }
}