package com.example.sankalp.safecity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Mylogin extends AppCompatActivity implements View.OnClickListener {

    EditText user,pass;
    Button login;
    ProgressDialog progressDialog;
    TextView signup;
    Intent intent,intent1;
    public String getemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylogin);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);

        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
        signup=(TextView)findViewById(R.id.fp);
        signup.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

    }
    public void userLogin()
    {
        final String username=user.getText().toString().trim();
        final String password=pass.getText().toString().trim();
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject jsonObject=new JSONObject(response);
                            if(!jsonObject.getBoolean("error"))
                            {

                                getemail=jsonObject.getString("mail");
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(),getemail,Toast.LENGTH_SHORT).show();
                                intent1=new Intent(Mylogin.this,selectComplaint.class);
                                intent1.putExtra("k",getemail);
                                startActivity(intent1);
                                finish();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return  params;
            }
        };
            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==login) {
            String a = user.getText().toString();
            String b = pass.getText().toString();
            if (!a.isEmpty() && !b.isEmpty())
                userLogin();
            else
            {
                if (a.isEmpty())
                {
                    user.setError("Please enter a username");
                    user.requestFocus();
                }
                if (b.isEmpty())
                {
                    pass.setError("Please enter a password");
                    pass.requestFocus();
                }
                return;

            }
        }
        if(v==signup)
        {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
