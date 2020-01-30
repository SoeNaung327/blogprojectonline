package com.itonemm.blogprojectonline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        WebView webView=findViewById(R.id.newsDetails);

        //Get Data From Main Activity
        Bundle b=getIntent().getExtras();
        String url=b.getString("url");
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
