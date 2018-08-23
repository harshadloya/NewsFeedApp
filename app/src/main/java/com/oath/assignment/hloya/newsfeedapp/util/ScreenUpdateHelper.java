package com.oath.assignment.hloya.newsfeedapp.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.oath.assignment.hloya.newsfeedapp.R;
import com.oath.assignment.hloya.newsfeedapp.fragments.InitialScreenFragment;
import com.oath.assignment.hloya.newsfeedapp.fragments.NewsFeedViewFragment;

public class ScreenUpdateHelper {

    /**
     * Updates the UI on the screen
     * @param fragment - new fragment that will be displayed on the UI
     * @param fragmentManager - fragment manager needed to perform the update
     * @param backStackStateName - name of the fragment being added to the stack
     */
    public static void performScreenUpdate(Fragment fragment, FragmentManager fragmentManager, String backStackStateName)
    {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(fragment instanceof InitialScreenFragment)
        {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        fragmentTransaction.replace(R.id.newsFeed_contentFragment, fragment);

        //Saving just the NewsStoryScreen to backstack so that when we do back, we can go to the prvious NewsFeed Screen
        if(!(fragment instanceof InitialScreenFragment) && !(fragment instanceof NewsFeedViewFragment)) {
            fragmentTransaction.addToBackStack(backStackStateName);
        }
        fragmentTransaction.commit();
    }
}
