package com.example.sankalp.safecity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText user,pass,em,num;
    Button register,login;
    ProgressDialog progressDialog;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        em=(EditText)findViewById(R.id.email);
        num=(EditText)findViewById(R.id.phone);
        register=(Button)findViewById(R.id.register);
        login=(Button)findViewById(R.id.login);
        progressDialog=new ProgressDialog(this);
        register.setOnClickListener(this);
        login.setOnClickListener(this);


    }

    void registerme()
    {
       final String username=user.getText().toString().trim();
        final String password=pass.getText().toString().trim();
        final String email=em.getText().toString().trim();
        final String number=num.getText().toString().trim();

        if(email.isEmpty())
        {
            em.setError("Invalid email");
            em.requestFocus();
            return;
        }
        if(!email.matches("[a-zA-Z0-9._]+@[a-z]+.[a-z]+"))
        {
            em.setError("Invalid email");
            em.requestFocus();
            return;
        }
        if(number.isEmpty())
        {
            num.setError("Invalid number");
            num.requestFocus();
            return;
        }
        if(!number.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))
        {
            num.setError("Invalid number");
            num.requestFocus();
            return;
        }


        progressDialog.setMessage("Registering User");
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {

                            JSONObject jsonObject=new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),Mylogin.class);
                            startActivity(i);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               progressDialog.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                params.put("email",email);
                params.put("phone_no",number);
                return params;
            }
        };
        //RequestQueue requestQueue= Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if(view==register) {

                registerme();
        }
        if(view==login)
        {
            intent=new Intent(MainActivity.this,Mylogin.class);
            startActivity(intent);
        }

    }
}
