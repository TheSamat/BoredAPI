package com.example.boredapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv_activity;
    TextView tv_link;
    TextView tv_price;
    TextView tv_type;
    private final String url = "http://www.boredapi.com/api/activity/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    void init() {
        tv_activity = findViewById(R.id.activity);

        tv_price = findViewById(R.id.price);
        tv_type = findViewById(R.id.type);
    }

    public void getBoredApiDetails(View view) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    String activity = jsonResponse.getString("activity");
                    String type = jsonResponse.getString("type");
                    String link = jsonResponse.getString("link");
                    Double price = jsonResponse.getDouble("price");
                    tv_activity.setText(activity);
                    tv_type.setText(type);
                    tv_price.setText(price+" $");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }
}
