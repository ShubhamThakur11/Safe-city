package com.example.sankalp.safecity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class selectComplaint extends AppCompatActivity implements View.OnClickListener {

    Intent intent=new Intent();

    Intent i;
    String mymail;
    ImageView img1,img2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_complaint);
        img1=(ImageView)findViewById(R.id.new_reg);
        img2=(ImageView)findViewById(R.id.already_reg);
        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        intent=getIntent();
        mymail=intent.getStringExtra("k");
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.new_reg)
        {
            i=new Intent(selectComplaint.this,userComplaint.class);
            i.putExtra("m",mymail);
            startActivity(i);

        }
        if(v.getId()==R.id.already_reg)
        {
           i=new Intent(selectComplaint.this,ShowRegisteredComplaint.class);
            i.putExtra("m",mymail);
            startActivity(i);

        }

    }
}
