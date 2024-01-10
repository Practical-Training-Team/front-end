package com.example.englishapp.databean;

public class Result {
    private String url;
    private Integer total_score;
    private Integer Completeness;
    private Integer Fluency;
    private Integer Standard;
    private Integer Accuracy;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTotal_score() {
        return total_score;
    }

    public void setTotal_score(Integer total_score) {
        this.total_score = total_score;
    }

    public Integer getCompleteness() {
        return Completeness;
    }

    public void setCompleteness(Integer completeness) {
        Completeness = completeness;
    }

    public Integer getFluency() {
        return Fluency;
    }

    public void setFluency(Integer fluency) {
        Fluency = fluency;
    }

    public Integer getStandard() {
        return Standard;
    }

    public void setStandard(Integer standard) {
        Standard = standard;
    }

    public Integer getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        Accuracy = accuracy;
    }

}
