package com.example.englishapp;

public class AdviceItem {
    private Integer article_id;
    private String title;
    private String category;

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public AdviceItem(Integer article_id, String title, String category) {
        this.article_id = article_id;
        this.title = title;
        this.category = category;
    }
}
