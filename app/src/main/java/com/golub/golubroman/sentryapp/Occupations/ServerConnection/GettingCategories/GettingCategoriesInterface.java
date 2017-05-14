package com.golub.golubroman.sentryapp.Occupations.ServerConnection.GettingCategories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by roman on 14.05.17.
 */

public interface GettingCategoriesInterface {
    @GET("/categories")
    Call<List<ResponsePOJO>> getCategories(@Header("Authorization") String accessToken);
}
