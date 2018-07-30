package com.example.sankalp.safecity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ShowRegisteredComplaint extends AppCompatActivity {
String email;
    WebView wb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_registered_complaint);
        email=getIntent().getExtras().getString("m").trim();
        String open=Constants.GET_COMP+"?m="+email;
        wb=(WebView)findViewById(R.id.mywebview);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl(open);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.opt1)
        {
            Intent intent=new Intent(ShowRegisteredComplaint.this,Selectlogin.class);
            startActivity(intent);
            finish();
        }



        return super.onOptionsItemSelected(item);
    }
}
