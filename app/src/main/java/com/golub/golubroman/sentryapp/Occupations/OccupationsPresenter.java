package com.golub.golubroman.sentryapp.Occupations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roman on 23.04.17.
 */

public class OccupationsPresenter implements OccupationsMVP.PInterface{

    private OccupationsMVP.VtPInterface view;

    private List<OccupationsModel> occupationsList = new ArrayList<>();


    public OccupationsPresenter(OccupationsMVP.VtPInterface view){
        this.view = view;
    }

}
