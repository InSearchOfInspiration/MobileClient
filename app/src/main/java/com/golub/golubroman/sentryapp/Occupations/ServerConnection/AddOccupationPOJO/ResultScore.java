 package com.golub.golubroman.sentryapp.Occupations.ServerConnection.AddOccupationPOJO;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

 public class ResultScore {

     @SerializedName("usefulness")
     @Expose
     private Integer usefulness;
     @SerializedName("pleasure")
     @Expose
     private Integer pleasure;
     @SerializedName("fatigue")
     @Expose
     private Integer fatigue;

     public Integer getUsefulness() {
         return usefulness;
     }

     public void setUsefulness(Integer usefulness) {
         this.usefulness = usefulness;
     }

     public Integer getPleasure() {
         return pleasure;
     }

     public void setPleasure(Integer pleasure) {
         this.pleasure = pleasure;
     }

     public Integer getFatigue() {
         return fatigue;
     }

     public void setFatigue(Integer fatigue) {
         this.fatigue = fatigue;
     }

 }