package com.oath.assignment.hloya.newsfeedapp.data;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Custom Async Task to Download the Image from the url found in the json file and set it to the News Feed Card ImageView
 */
public class GetNewsImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView imageView;
    public GetNewsImageTask(ImageView imgView)
    {
        imageView = imgView;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        String urlToDownloadFrom = strings[0];
        Bitmap imageBitmap = null;

        try {
            BitmapFactory.Options bmo = new BitmapFactory.Options();
            bmo.inSampleSize = 1;
            InputStream in = new URL(urlToDownloadFrom).openStream();
            imageBitmap = BitmapFactory.decodeStream(in, null, bmo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageBitmap;
    }

    /**
     * Method that runs immediately after the task is completed to set the image downloaded into imageview
     * @param fetchedImage - Image downloaded
     */
    @Override
    protected void onPostExecute(Bitmap fetchedImage) {
        //super.onPostExecute(bitmap);
        imageView.setImageBitmap(fetchedImage);
    }
}
