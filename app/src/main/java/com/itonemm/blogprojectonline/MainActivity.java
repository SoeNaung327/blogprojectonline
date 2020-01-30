package com.itonemm.blogprojectonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView list=findViewById(R.id.news);

        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=3131dafe43084f8c8f20bcae322f87f4";

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            ArrayList<NewsModel> newsModels = new ArrayList<NewsModel>();
                            JSONArray myarticles = response.getJSONArray("articles");
                            for (int i = 0; i < myarticles.length(); i++) {
                                JSONObject obj = myarticles.getJSONObject(i);
                                NewsModel model = new NewsModel();
                                model.author = obj.getString("author");
                                model.imageUrl = obj.getString("urlToImage");
                                model.title = obj.getString("title");
                                model.newsUrl = obj.getString("url");
                                model.publishedDate = obj.getString("publishedAt");
                                newsModels.add(model);
                            }

                            NewsAdapter adapter=new NewsAdapter(newsModels,getApplicationContext(),getParent());
                            list.setAdapter(adapter);
                            LinearLayoutManager lm=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                            list.setLayoutManager(lm);
                            progressBar.setVisibility(View.GONE);
                            list.setVisibility(View.VISIBLE);

                        } catch (Exception ex) {
                            Log.e("Error", ex.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 60000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 10;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        queue.add(request);

// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: "+ response.substring(0,500));
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
//            }
//        });
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);

    }
}
