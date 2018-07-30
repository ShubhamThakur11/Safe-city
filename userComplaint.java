package com.example.sankalp.safecity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class userComplaint extends AppCompatActivity implements View.OnClickListener{

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    double lat,lon;
    //public Button button;
    public LocationManager locationManager;
    public LocationListener locationListener;
    TextView welcome, dateview,timeview;
    EditText location,date,time,complaint;
    Button submit, button_stpd;
    static final int DIALOG_ID = 0;
    int hour_x, min_x;
    String getmail;
    Intent intent=new Intent();
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint);
        welcome=(TextView)findViewById(R.id.title);
        location=(EditText)findViewById(R.id.location);
        time=(EditText)findViewById(R.id.time);
        complaint=(EditText)findViewById(R.id.complaint);
        submit=(Button)findViewById(R.id.submit);
        timeview=(TextView) findViewById(R.id.textView4);
        dateview = (TextView)findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //button = (Button) findViewById(R.id.button);
        //showDate(year, month+1, day);
        location.setOnClickListener(this);
        //button.setOnClickListener(this);
        submit.setOnClickListener(this);
        intent=getIntent();
        getmail=intent.getStringExtra("m");
        //Toast.makeText(getApplicationContext(),getmail,Toast.LENGTH_SHORT).show();
        welcome.setText("Hello "+getmail);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        showTimePickerDialog();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener= new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat=location.getLatitude();
                lon=location.getLongitude();
                //Toast.makeText(getApplicationContext(),"\n" + lat + " " + lon,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {
                Toast.makeText(getApplicationContext(),"Location has been enabled",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    configureButton();
                }
                return;
        }
    }

    public void configureButton() {
        onClick(location);

    }


    public void showTimePickerDialog()
    {
        button_stpd = (Button)findViewById(R.id.SetTime);
        button_stpd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(DIALOG_ID);
                    }
                }

        );
    }



    protected TimePickerDialog.OnTimeSetListener kTimePickerListner = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute){
            hour_x = hourOfDay;
            min_x = minute;
            timeview.setText(new StringBuilder().append(hour_x).append(":").append(min_x));
        }
    };

    public void setDate(View view)
    {
        showDialog(999);
        //Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id == 999)
            return new DatePickerDialog(this, myDateListener, year, month, day);
        if (id == DIALOG_ID)
            return new TimePickerDialog(userComplaint.this, kTimePickerListner, hour_x, min_x, false);
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker arg0, int arg1,int arg2,int arg3)
        {
            showDate(arg1,arg2+1,arg3);
        }
    };
    private void showDate(int year,int month,int day)
    {
        dateview.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
    }
    public void Complaintregister()
    {
        final String loc=location.getText().toString().trim();
        final String dt=dateview.getText().toString().trim();
        final String tm=timeview.getText().toString().trim();
        final String comp=complaint.getText().toString().trim();
        final String latits=""+lat;
        final String longits=""+lon;
        //Toast.makeText(getApplicationContext(),latits+" "+longits,Toast.LENGTH_SHORT).show();
        progressDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Constants.Complaint_reg,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject jsonObject=new JSONObject(response);
                            if(!jsonObject.getBoolean("error"))
                            {


                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(),"Your Complaint id is:"+jsonObject.getString("id"),Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplicationContext(),mynotify.class);
                                i.putExtra("id",jsonObject.getString("id"));
                                startService(i);
                                finish();


                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("location",loc);
                params.put("mydate",dt);
                params.put("mytime",tm);
                params.put("mycomplaint",comp);
                params.put("email",getmail);
                params.put("mylatit",latits);
                params.put("mylongit",longits);
                return  params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onClick(View v) {
        if(v==submit)
        {
            Complaintregister();
        }
        if(v==location){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                }, 10);
                return;
            }
            locationManager.requestLocationUpdates("gps", 5000,0, locationListener);
        }
        //Toast.makeText(getApplicationContext(),"toast",Toast.LENGTH_SHORT).show();

    }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opt1) {
            Intent intent = new Intent(userComplaint.this, Selectlogin.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
