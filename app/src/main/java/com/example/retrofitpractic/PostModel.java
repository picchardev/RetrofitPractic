package com.example.retrofitpractic;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    private String Id;
    private String UserId;
    private String Title;

    @SerializedName("body")
    private String Body;

    public PostModel(String id, String userId, String title, String body) {
        Id = id;
        UserId = userId;
        Title = title;
        Body = body;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }
}
