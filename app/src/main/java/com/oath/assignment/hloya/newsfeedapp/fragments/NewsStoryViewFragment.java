package com.oath.assignment.hloya.newsfeedapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.oath.assignment.hloya.newsfeedapp.R;

/**
 * Fragment that shows the full content of a NewsFeed
 */
public class NewsStoryViewFragment extends Fragment {

    private String newsURL;
    private WebView webview;

    public void setNewsURL(String url)
    {
        newsURL = url;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_story_view, container, false);

        webview = (WebView) view.findViewById(R.id.newsStory_WebView);

        //Setting the browser to be in-app
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        webview.loadUrl(newsURL);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    //Setting the Action Bar Elements to show the back arrow "<" for up navigation and the title as "Done"
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.titleBarText_NewsStory);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_arrow);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onActivityCreated(savedInstanceState);
    }
}
