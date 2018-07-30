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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class adminLogin extends AppCompatActivity implements View.OnClickListener{

    EditText admin,pass;
    Button login;
    ProgressDialog progressDialog;
    //TextView fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        admin=(EditText)findViewById(R.id.adminid);
        pass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
      //fp=(TextView)findViewById(R.id.fp);
        //fp.setOnClickListener(this);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        login.setOnClickListener(this);
    }

    public void adminLogin()
    {
        final String userid=admin.getText().toString().trim();
        final String password=pass.getText().toString().trim();
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.Admin_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject jsonObject=new JSONObject(response);
                            if(!jsonObject.getBoolean("error"))
                            {


                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),checkComplaint.class);
                                startActivity(i);
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
                params.put("userid",userid);
                params.put("password",password);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v==login)
        {

        String x = admin.getText().toString();
        String b = pass.getText().toString();
        if (!x.isEmpty() && !b.isEmpty())
            adminLogin();
        else
        {
            if (x.isEmpty())
            {
                admin.setError("Please enter an id");
                admin.requestFocus();
            }
            if (b.isEmpty())
            {
                pass.setError("Please enter a password");
                pass.requestFocus();
            }
            return;

        }
        //if(v==fp)
        //{
         //   Intent i = new Intent(getApplicationContext(),passwordReset.class);
           // startActivity(i);
        //}
    }
    }
}
