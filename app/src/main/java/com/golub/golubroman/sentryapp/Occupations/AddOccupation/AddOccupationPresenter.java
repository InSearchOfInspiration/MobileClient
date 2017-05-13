package com.golub.golubroman.sentry.Occupations;

import com.golub.golubroman.sentryapp.Occupations.OccupationsModel;

import java.util.ArrayList;
import java.util.List;

public class AddOccupationsPresenter implements AddOccupationsMVP.PInterface{

    private AddOccupationsMVP.VtPInterface view;

    private List<OccupationsModelModel> occupationsList = new ArrayList<>();


    public AddOccupationsPresenter(AddOccupationMVP.VtPInterface view){
        this.view = view;
    }

}
