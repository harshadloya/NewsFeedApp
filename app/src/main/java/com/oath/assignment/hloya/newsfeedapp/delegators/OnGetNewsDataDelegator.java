package com.oath.assignment.hloya.newsfeedapp.delegators;

import com.oath.assignment.hloya.newsfeedapp.model.Card;

import java.util.List;

public interface OnGetNewsDataDelegator {
    void onGetNewsDataCompleted(List<Card> cardList);
    void updateLoadingProgressBar(Integer i);
}
