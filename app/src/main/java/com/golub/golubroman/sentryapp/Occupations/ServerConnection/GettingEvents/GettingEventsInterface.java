package com.golub.golubroman.sentryapp.Occupations.ServerConnection.GettingEvents;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by roman on 14.05.17.
 */

public interface GettingEventsInterface {
    @GET("/events")
    Call<List<ResponsePOJO>> getEvents(@Header("Authorization") String accessToken);
}

