package com.golub.golubroman.sentryapp.Occupations.ServerConnection.RegistrationPOJO;

import com.golub.golubroman.sentry.ServerConnection.AuthorizationPOJO.ResponsePOJO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by roman on 14.05.17.
 */

public interface RegistrationInterface {
    @POST("/registry")
    Call<ResponsePOJO> registerUser(@Body RequestPOJO requestPOJO);

    //Call<OccupationPOJO> getData(@Query("username") String username, @Query("password")String password);
}
