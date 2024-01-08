package com.example.englishapp.databean;


public class ArticlePage {
    private String title;
    private String content;
    private String category;
    private Integer likes;
    private Integer page_view;
    private String release_time;

    public ArticlePage(String title, String content, String category, Integer likes, Integer page_view, String release_time) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.likes = likes;
        this.page_view = page_view;
        this.release_time = release_time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getPage_view() {
        return page_view;
    }

    public String getRelease_time() {
        return release_time;
    }

}
