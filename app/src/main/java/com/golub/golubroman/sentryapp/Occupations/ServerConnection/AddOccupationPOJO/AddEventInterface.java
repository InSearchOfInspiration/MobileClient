package com.golub.golubroman.sentryapp.Occupations.ServerConnection.AddOccupationPOJO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by roman on 14.05.17.
 */

public interface AddEventInterface {
    @POST("/events/new")
    Call<String> addNew(@Header("Authorization") String authorization, @Body OccupationPOJO occupationPOJO);
}
