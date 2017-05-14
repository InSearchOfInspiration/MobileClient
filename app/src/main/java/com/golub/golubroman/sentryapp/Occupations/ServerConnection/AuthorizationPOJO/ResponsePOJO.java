package com.golub.golubroman.sentryapp.Occupations.ServerConnection.AuthorizationPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePOJO {

    @SerializedName("access_token")
    @Expose
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}