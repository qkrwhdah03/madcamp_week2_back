package com.example.week2;

public interface HttpCallback {
    void onSuccess(String result);
    void onFailure(Exception e);
}
