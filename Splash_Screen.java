package com.example.sankalp.safecity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        ImageView imageView;
        imageView=(ImageView)findViewById(R.id.imageView2) ;
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        imageView.startAnimation(animation);
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(6000);
                    Intent intent=new Intent(Splash_Screen.this,Selectlogin.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
