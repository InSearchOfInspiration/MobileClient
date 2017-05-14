package com.golub.golubroman.sentryapp.Occupations.ServerConnection.LocationTracking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

/**
 * Created by roman on 14.05.17.
 */

public interface LocationTrackingInterface {
    @PUT("/coordinates")
    Call<String> registerUser(@Header("Authorization") String s, @Body RequestPOJO requestPOJO);
}
