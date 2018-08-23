package com.oath.assignment.hloya.newsfeedapp.data;

import android.os.AsyncTask;

import com.oath.assignment.hloya.newsfeedapp.delegators.OnGetNewsDataDelegator;
import com.oath.assignment.hloya.newsfeedapp.model.Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetNewsDataTask extends AsyncTask<Void, Integer, List<Card>> {

    private final OnGetNewsDataDelegator onGetNewsDataDelegator;

    public GetNewsDataTask(OnGetNewsDataDelegator listener) {
        onGetNewsDataDelegator = listener;
    }

    @Override
    protected List<Card> doInBackground(Void... voids) {
        URL newsFeedApi = null;
        List<Card> cardsList=null;

        try {
            newsFeedApi = new URL("https://pubapps.github.io/newsfeed.json");
            HttpURLConnection connection = (HttpURLConnection) newsFeedApi.openConnection();

            if(connection.getResponseCode() == 200)
            {
                cardsList = new ArrayList<>();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer jsonStr = new StringBuffer();
                String tmp="";
                while((tmp=reader.readLine())!=null)
                    jsonStr.append(tmp).append("\n");
                reader.close();

                if(jsonStr != null || !jsonStr.equals(""))
                {
                    try {
                        JSONObject jsonObject = new JSONObject(jsonStr.toString());
                        JSONArray result = jsonObject.getJSONObject("items").getJSONArray("result");

                        int resultLen = result.length();
                        for(int i=0; i<resultLen; i++)
                        {
                            JSONObject newsObject = result.getJSONObject(i);
                            JSONObject newsContentObject = newsObject.getJSONObject("content");

                            Card newsCard = new Card();
                            newsCard.setTitle(newsContentObject.getString("title"));
                            newsCard.setUrl(newsContentObject.getString("url"));

                            JSONArray newsImagesArray = newsContentObject.getJSONArray("images");
                            //JSONArray newsImageResolutionsArray = newsImagesArray.getJSONObject(0).getJSONArray("resolutions");
                            newsCard.setImageURL(newsImagesArray.getJSONObject(0).getString("originalUrl"));

                            cardsList.add(i, newsCard);
                            publishProgress(((i+1/resultLen)*100));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cardsList;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {

        if(onGetNewsDataDelegator != null)
        {
            onGetNewsDataDelegator.updateLoadingProgressBar(values[0]);
        }
        super.onProgressUpdate(values);

    }

    @Override
    protected void onPostExecute(List<Card> cardList) {
        //super.onPostExecute(cardList);
        if(onGetNewsDataDelegator != null)
        {
            onGetNewsDataDelegator.onGetNewsDataCompleted(cardList);
        }
    }
}
