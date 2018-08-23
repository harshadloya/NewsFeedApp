package com.oath.assignment.hloya.newsfeedapp.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oath.assignment.hloya.newsfeedapp.R;
import com.oath.assignment.hloya.newsfeedapp.data.GetNewsImageTask;
import com.oath.assignment.hloya.newsfeedapp.delegators.OnNewsCardClickDelegator;
import com.oath.assignment.hloya.newsfeedapp.model.Card;
import java.util.List;

/**
 * Recycler View Adaptor to Handle Displaying the News Feed Cards
 */
public class NewsCardRecyclerViewAdapter extends RecyclerView.Adapter<NewsCardRecyclerViewAdapter.NewsCardsViewHolder> {

    private OnNewsCardClickDelegator onNewsCardClickDelegator;
    private List<Card> newsFeedCardList;

    public NewsCardRecyclerViewAdapter(List<Card> list)
    {
        newsFeedCardList = list;
    }

    public void setOnNewsCardClickDelegator(OnNewsCardClickDelegator clickListener) {
        this.onNewsCardClickDelegator = clickListener;
    }

    @NonNull
    @Override
    public NewsCardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_cards_view, parent, false);
        NewsCardsViewHolder newsCardsViewHolder = new NewsCardsViewHolder(view);
        return newsCardsViewHolder;
    }

    /**
     * This method sets data for each News Feed Card Created
     * @param holder - Contains all the views that need to be set for each of the newsfeed card
     * @param position - Needed to iterate through the NewsCardsViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull NewsCardsViewHolder holder, int position) {
        holder.newsCardViewText.setText(newsFeedCardList.get(position).getTitle());
        new GetNewsImageTask(holder.newsCardViewImage).execute(newsFeedCardList.get(position).getImageURL());

        final String newsURL = newsFeedCardList.get(position).getUrl();
        holder.newsFeedCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewsCardClickDelegator.onNewsCardClick(newsURL);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(newsFeedCardList != null)
            return newsFeedCardList.size();
        return 0;

    }

    /**
     * Custom Holder which extends the recyclersView.ViewHolder's functionality to provide holder for our News Feed(Custom) Content
     */
    class NewsCardsViewHolder extends RecyclerView.ViewHolder {

        CardView newsFeedCardView;
        TextView newsCardViewText;
        ImageView newsCardViewImage;

        public NewsCardsViewHolder(View itemView) {
            super(itemView);

            newsCardViewText = (TextView) itemView.findViewById(R.id.newsFeed_CardView_text);
            newsCardViewImage = (ImageView) itemView.findViewById(R.id.newsFeed_CardView_image);
            newsFeedCardView = (CardView) itemView.findViewById(R.id.newsFeed_CardView);
        }
    }
}
