package com.example.englishapp.databean;

public class RankItem {
    private Integer id;
    private Integer score;
    private String name;
    private Integer rank;

    public RankItem(Integer id, Integer score, String name, Integer rank) {
        this.id = id;
        this.score = score;
        this.name = name;
        this.rank = rank;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
