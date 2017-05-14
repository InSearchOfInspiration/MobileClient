package com.golub.golubroman.sentryapp.Occupations.ServerConnection.AuthorizationPOJO;

import com.golub.golubroman.sentry.ServerConnection.AddOccupationPOJO.AddEventInterface;
import com.golub.golubroman.sentry.ServerConnection.GettingCategories.GettingCategoriesInterface;
import com.golub.golubroman.sentry.ServerConnection.GettingEvents.GettingEventsInterface;
import com.golub.golubroman.sentry.ServerConnection.LocationTracking.LocationTrackingInterface;
import com.golub.golubroman.sentry.ServerConnection.RegistrationPOJO.RegistrationInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitModule{
    private static AuthorizationInterface authorizationInterface;
    private static RegistrationInterface registrationInterface;
    private static LocationTrackingInterface locationTrackingInterface;
    private static GettingCategoriesInterface gettingCategoriesInterface;
    private static GettingEventsInterface gettingEventsInterface;
    private static AddEventInterface addEventInterface;

    private static Retrofit retrofit;
    public static void buildAuthApi(){
        buildApi();
        authorizationInterface = retrofit.create(AuthorizationInterface.class);
    }
    public static void buildRegApi(){
        buildApi();
        registrationInterface = retrofit.create(RegistrationInterface.class);
    }
    public static void buildLocApi(){
        buildApi();
        locationTrackingInterface = retrofit.create(LocationTrackingInterface.class);
    }
    public static void buildCatApi(){
        buildApi();
        gettingCategoriesInterface = retrofit.create(GettingCategoriesInterface.class);
    }
    public static void buildEveApi(){
        buildApi();
        gettingEventsInterface = retrofit.create(GettingEventsInterface.class);
    }
    public static void buildAddEvApi(){
        buildApi();
        addEventInterface = retrofit.create(AddEventInterface.class);
    }
    private static void buildApi(){
        retrofit = new Retrofit.Builder().
                baseUrl("http://10.55.42.159:5000/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public static AuthorizationInterface getAuthApi(){
        return authorizationInterface;
    }
    public static RegistrationInterface getRegApi(){
        return registrationInterface;
    }
    public static LocationTrackingInterface getLocApi(){
        return locationTrackingInterface;
    }
    public static GettingCategoriesInterface getCatApi(){
        return gettingCategoriesInterface;
    }
    public static GettingEventsInterface getEveApi(){
        return gettingEventsInterface;
    }
    public static AddEventInterface getAddEvApi(){
        return addEventInterface;
    }

}

