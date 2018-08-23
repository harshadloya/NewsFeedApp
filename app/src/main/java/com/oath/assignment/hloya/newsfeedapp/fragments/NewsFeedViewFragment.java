package com.oath.assignment.hloya.newsfeedapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oath.assignment.hloya.newsfeedapp.R;
import com.oath.assignment.hloya.newsfeedapp.delegators.OnNewsCardClickDelegator;
import com.oath.assignment.hloya.newsfeedapp.model.Card;
import com.oath.assignment.hloya.newsfeedapp.util.NewsCardRecyclerViewAdapter;
import com.oath.assignment.hloya.newsfeedapp.util.ScreenUpdateHelper;

import java.util.List;

/**
 * Fragment that Displays the NewsFeed Card Layout and creates as many NewsFeeds as available through the RecyclerView
 */
public class NewsFeedViewFragment extends Fragment {

    private List<Card> newsCardsList;
    private RecyclerView newsFeedRecyclerView;
    private RecyclerView.Adapter newsFeedRecyclerAdapter;
    private RecyclerView.LayoutManager newsFeedRecyclerLayoutManager;

    public void setNewCardsList(List<Card> cardList)
    {
        newsCardsList = cardList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);

        newsFeedRecyclerView = (RecyclerView) view.findViewById(R.id.newsFeed_recyclerView);
        newsFeedRecyclerView.setHasFixedSize(true);
        newsFeedRecyclerLayoutManager = new LinearLayoutManager(getContext());
        newsFeedRecyclerView.setLayoutManager(newsFeedRecyclerLayoutManager);

        //Sets the Cards List obtained through the async task from the API to the Recycler View
        newsFeedRecyclerAdapter = new NewsCardRecyclerViewAdapter(newsCardsList);
        newsFeedRecyclerView.setAdapter(newsFeedRecyclerAdapter);

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        //When a news feed is clicked its full content will be shown by the NewsStoryViewFragment
        ((NewsCardRecyclerViewAdapter) newsFeedRecyclerAdapter).setOnNewsCardClickDelegator(new OnNewsCardClickDelegator() {
            @Override
            public void onNewsCardClick(String newsURL) {
                NewsStoryViewFragment newsStoryViewFragment = new NewsStoryViewFragment();
                newsStoryViewFragment.setNewsURL(newsURL);
                ScreenUpdateHelper.performScreenUpdate(newsStoryViewFragment, getFragmentManager(), "replaceWithNewsStoryScreen");
                Log.i("NewsFeedViewFragment", " Clicked on a News");
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //Setting the new title
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.titleBarText_NewsFeed);

        //Disabling the UP navigation as we have reached the parent screen
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        //Removing the custom icon set for the up navigation
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeAsUpIndicator(null);
        super.onActivityCreated(savedInstanceState);
    }
}