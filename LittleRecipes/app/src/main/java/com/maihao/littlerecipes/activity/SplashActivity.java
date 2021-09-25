
package com.maihao.littlerecipes.activity;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.maihao.littlerecipes.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 全面屏配置
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        // 开一个线程去开活动吧
        new Thread(() -> {
            try {
                sleep(3000);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            } catch (InterruptedException e) {
                finish();
            }
        }).start();

        // 说不定在这个页面还要完成其他逻辑呢？
    }
}