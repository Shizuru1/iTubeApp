package com.example.itubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    String videoEmbed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);

        videoEmbed = getIntent().getStringExtra("url");
        if (videoEmbed.contains("watch?v=")) {
            videoEmbed = videoEmbed.replace("watch?v=", "embed/");
        }
        if (videoEmbed.contains("youtu.be")) {
            videoEmbed = videoEmbed.replace("youtu.be", "www.youtube.com");
        }
        if (!videoEmbed.contains("http")) {
            videoEmbed = "https://".concat(videoEmbed);
        }

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:player.playerVideo();");
            }
        });

        webView.loadData(
                "<html>" +
                        "<body>" +
                        "<iframe width=\"100%\" height=\"100%\" src=\"" + videoEmbed + "?enablejsapi=1\" frameborder=\"0\" allowfullscreen>" +
                        "</iframe>" +
                        "</body>" +
                        "</html>",
                "text/html",
                "utf-8"
        );
    }
}