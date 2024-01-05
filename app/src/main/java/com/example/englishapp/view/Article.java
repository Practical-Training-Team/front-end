package com.example.englishapp.view;


public class Article {

    private String id;
    private String title;
    private String content;
    private Integer likes;
    private Integer page_view;
    private String release_time;
    private String category;

    public Article(String id, String title, String content, String category, Integer likes, Integer pageView, String releaseTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.page_view = pageView;
        this.release_time = releaseTime;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getPageView() {
        return page_view;
    }

    public void setPageView(Integer pageView) {
        this.page_view = pageView;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
