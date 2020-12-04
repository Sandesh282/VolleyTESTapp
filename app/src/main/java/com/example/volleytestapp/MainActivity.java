package com.example.volleytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
// Here we are dealing with parsing of TODO:// JSON Objects
    RequestQueue mRequestQueue;
    RequestQueue mRequestQueueArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueueArray = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest
                = new JsonObjectRequest(Request.Method.GET,
                "https://official-joke-api.appspot.com/random_joke", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("RESPONSE VALID DATA", response + "");

// accessing object elements
                try{
                    int jokeID = response.getInt("id");
                    Toast.makeText(getApplicationContext(), Integer.toString(jokeID), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Your internet is gone. PLease try again!!!", Toast.LENGTH_SHORT).show();
                }

                try{
                    String jokeType = response.getString("type");
                    Toast.makeText(getApplicationContext(), jokeType, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Your internet is gone. PLease try again!!!", Toast.LENGTH_SHORT).show();
                }

                try{
                    String jokeSetup = response.getString("setup");
                    Toast.makeText(getApplicationContext(), jokeSetup, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Your internet is gone. PLease try again!!!", Toast.LENGTH_SHORT).show();
                }

                try{
                    String jokePunchline = response.getString("punchline");
                    Toast.makeText(getApplicationContext(), jokePunchline, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "Your internet is gone. PLease try again!!!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("RESPONSE ERROR", error.getMessage());
            }
        });

        mRequestQueue.add(jsonObjectRequest);

// Here we are dealing with TODO:// JSON Arrays
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://official-joke-api.appspot.com/random_ten", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("Array Response", response+"");

                for (int index = 0; index < response.length(); index++){
                    try{
                        JSONObject jokeJsonObject = response.getJSONObject(index);
                        Log.i("JOKE", jokeJsonObject.getString("setup")
                                + " - " + jokeJsonObject.getString("punchline"));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
// accessing array elements

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Array Error", error.getMessage());

            }
        });

        mRequestQueueArray.add(jsonArrayRequest);

    }
}