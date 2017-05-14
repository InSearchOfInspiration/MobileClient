package com.golub.golubroman.sentryapp.Occupations.ServerConnection.AddOccupationPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OccupationPOJO {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("finish_date")
    @Expose
    private String finishDate;
    @SerializedName("source_type")
    @Expose
    private Integer sourceType;
    @SerializedName("result_score")
    @Expose
    private ResultScore resultScore;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("location")
    @Expose
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public ResultScore getResultScore() {
        return resultScore;
    }

    public void setResultScore(ResultScore resultScore) {
        this.resultScore = resultScore;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}