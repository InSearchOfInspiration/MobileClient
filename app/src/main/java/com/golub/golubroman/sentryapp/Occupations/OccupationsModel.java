package com.golub.golubroman.sentryapp.Occupations;

public class OccupationsModel {

    private String occupationsName;
    private String occupationsColor;
    private int occupationsIcon;
    private int id;
    private int time;
    private int occupationsUsefulness;
    private int occupationsPleasure;
    private int occupationsFatigue;

    public OccupationsModel(String occupationsName,
                            String backgroundColor, int occupationsIcon) {
        this.occupationsName = occupationsName;
        this.occupationsColor = backgroundColor;
        this.occupationsIcon = occupationsIcon;
    }

    public OccupationsModel(int id, String occupationsName, String backgroundColor, int occupationsIcon,
                            int occupationsUsefulness, int occupationsPleasure, int occupationsFatigue) {
        this.occupationsName = occupationsName;
        this.occupationsColor = backgroundColor;
        this.occupationsIcon = occupationsIcon;
        this.occupationsUsefulness = occupationsUsefulness;
        this.occupationsPleasure = occupationsPleasure;
        this.occupationsFatigue = occupationsFatigue;
        this.id = id;
    }

    public OccupationsModel(String occupationsName, String backgroundColor,
                            int occupationsIcon, int time, int occupationsUsefulness,
                            int occupationsPleasure, int occupationsFatigue) {
        this.occupationsName = occupationsName;
        this.occupationsColor = backgroundColor;
        this.occupationsIcon = occupationsIcon;
        this.occupationsUsefulness = occupationsUsefulness;
        this.occupationsPleasure = occupationsPleasure;
        this.occupationsFatigue = occupationsFatigue;
    }

    public String getOccupationsName() {
        return occupationsName;
    }

    public String getOccupationsColor() {
        return occupationsColor;
    }

    public int getOccupationsIcon() {
        return occupationsIcon;
    }

    public int getTime() {
        return time;
    }

    public int getOccupationsUsefulness() {
        return occupationsUsefulness;
    }

    public int getOccupationsPleasure() {
        return occupationsPleasure;
    }

    public int getOccupationsFatigue() {
        return occupationsFatigue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}