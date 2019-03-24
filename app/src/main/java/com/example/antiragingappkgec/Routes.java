/*package com.example.antiragingappkgec;

import android.util.Log;

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

public class Routes {
    final static String url="http://192.168.42.185:2000";
    static String token;
    RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.class);
    public static JsonObjectRequest getHome=new JsonObjectRequest(
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
            headers.put("Authorization", "Bearer "+Routes.token);
            return headers;
        }
    };
    JsonObjectRequest postLogin=new JsonObjectRequest(
            Request.Method.POST,
            url + "/passauth/login?username=admin&password=admin",
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Routes.token=response.getString("token");
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
            }
    );
}
*/