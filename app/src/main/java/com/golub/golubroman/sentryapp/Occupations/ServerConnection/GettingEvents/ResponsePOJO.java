package com.golub.golubroman.sentryapp.Occupations.ServerConnection.GettingEvents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePOJO {

    @SerializedName("source_type")
    @Expose
    private Integer sourceType;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("finish_date")
    @Expose
    private String finishDate;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}