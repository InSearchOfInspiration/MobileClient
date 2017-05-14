package com.golub.golubroman.sentryapp.Occupations.ServerConnection.AuthorizationPOJO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by roman on 14.05.17.
 */

public interface AuthorizationInterface {
    @POST("/login")
    Call<ResponsePOJO> registerUser(@Body RequestPOJO requestPOJO);

    //Call<OccupationPOJO> getData(@Query("username") String username, @Query("password")String password);
}
