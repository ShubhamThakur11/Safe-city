package com.example.sankalp.safecity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Selectlogin extends AppCompatActivity implements View.OnClickListener{
ImageView a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectlogin);
        a=(ImageView)findViewById(R.id.imageView2);
        b=(ImageView)findViewById(R.id.imageView3);
        c=(ImageView)findViewById(R.id.imageView4);
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case  R.id.imageView2:
                Intent i = new Intent(getApplicationContext(),Mylogin.class);
                startActivity(i);
                break;
            case R.id.imageView3:
                Intent x = new Intent(getApplicationContext(),adminLogin.class);
                startActivity(x);
                break;
            case R.id.imageView4:
                Intent im = new Intent(getApplicationContext(),AboutUs.class);
                startActivity(im);
        }
    }
}
