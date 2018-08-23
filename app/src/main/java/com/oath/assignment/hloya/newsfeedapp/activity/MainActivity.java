package com.oath.assignment.hloya.newsfeedapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.oath.assignment.hloya.newsfeedapp.R;
import com.oath.assignment.hloya.newsfeedapp.fragments.InitialScreenFragment;
import com.oath.assignment.hloya.newsfeedapp.util.ScreenUpdateHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Show Initial Screen
        InitialScreenFragment initialScreenFragment = new InitialScreenFragment();
        ScreenUpdateHelper.performScreenUpdate(initialScreenFragment, getSupportFragmentManager(), "replaceWithInitialSplashScreen");
     }

     //If there are fragments in the backstack, remove them when back is pressed else exit the app
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    //Overriding below to handle up navigation
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            //Up Navigation will always return android.R.id.home as item id
            case android.R.id.home :

                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}