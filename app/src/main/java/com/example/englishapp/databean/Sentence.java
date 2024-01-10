package com.example.englishapp.databean;

public class Sentence {


    private int sentence_id;
    private String content;

    private String url;



    private int category;

    public int getSentence_id() {
        return sentence_id;
    }

    public void setSentence_id(int sentence_id) {
        this.sentence_id = sentence_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Sentence(int id, int category, String content, String url) {
        this.sentence_id = id;
        this.content = content;
        this.url = url;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
