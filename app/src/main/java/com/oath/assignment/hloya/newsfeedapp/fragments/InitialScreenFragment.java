package com.oath.assignment.hloya.newsfeedapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.oath.assignment.hloya.newsfeedapp.R;
import com.oath.assignment.hloya.newsfeedapp.data.GetNewsDataTask;
import com.oath.assignment.hloya.newsfeedapp.delegators.OnGetNewsDataDelegator;
import com.oath.assignment.hloya.newsfeedapp.model.Card;
import com.oath.assignment.hloya.newsfeedapp.util.ScreenUpdateHelper;

import java.util.List;

/**
 * Initial Screen to show Loading Progress as Data is being read from the API
 */
public class InitialScreenFragment extends Fragment implements OnGetNewsDataDelegator {

    private ProgressBar loadingProgressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.initial_screen_main, container, false);

        loadingProgressBar = (ProgressBar) view.findViewById(R.id.newsFeed_loading_progressBar);

        //calling the task that will fetch the data from the api in the background
        GetNewsDataTask getNewsDataTask = new GetNewsDataTask(this);
        getNewsDataTask.execute();

        return view;
    }

    @Override
    public void onGetNewsDataCompleted(List<Card> cardList) {
        NewsFeedViewFragment newsFeedViewFragment = new NewsFeedViewFragment();
        newsFeedViewFragment.setNewCardsList(cardList);
        ScreenUpdateHelper.performScreenUpdate(newsFeedViewFragment, getFragmentManager(), "replaceWithNewsFeedScreen");
    }

    @Override
    public void updateLoadingProgressBar(Integer i) {
        loadingProgressBar.setProgress(i);
    }
}
