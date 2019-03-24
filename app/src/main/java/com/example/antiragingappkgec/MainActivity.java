package com.example.antiragingappkgec;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Auth authenticator=new Auth();
    EditText username,password;
    final static String url="http://192.168.42.26:2000";//IP address of the Backend server Connected to a P2P enabled Network and accessible from the development test Device
    RequestQueue requestQueue;
    JsonObjectRequest getProfile;
        public JsonObjectRequest postLoginf(String usernameStr,String passwordStr){
        JsonObjectRequest postLogin=new JsonObjectRequest(
            Request.Method.POST,
            url + "/passauth/login?username="+usernameStr+"&password="+passwordStr,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        authenticator.token=response.getString("token");
                        Log.e("Rest API Login Token", response.getString("token"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                Log.e("RestAPI Login Error",error.toString());
            }
        });
        return postLogin;
    }
    JsonObjectRequest getHome=new JsonObjectRequest(
            Request.Method.GET,
            url,
            (String) null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.e("Rest API Response",response.toString());
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Rest API Error Response",error.toString());
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        //Button button = (Button) findViewById(R.id.LoginBtn);
        requestQueue= Volley.newRequestQueue(this);


        /*
        //Dummy Protected Route Access Using JWT token
        JsonObjectRequest getProfile=new JsonObjectRequest(
                Request.Method.GET,
                url+"/passauth/profile",
                (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("Rest API Response",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest API Error Response",error.toString());
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer "+authenticator.token);
                return headers;
            }
        };
        */
        //Dummy login for getting access Token from JWT
        /*
        requestQueue.add(getHome);
        requestQueue.add(postLogin);
        requestQueue.add(getProfile);//Sends false as Token is not ready yet and all actions takes place in async
        */

    }
    public void onClickLogin(View view)
    {
        Log.e("Credentials",username.getText()+"&&"+password.getText());
        requestQueue.add(this.postLoginf(username.getText().toString(),password.getText().toString()));
    }
}
