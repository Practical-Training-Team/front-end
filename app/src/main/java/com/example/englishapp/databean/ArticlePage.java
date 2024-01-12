package com.example.englishapp.databean;

import java.util.List;
public class ArticlePage {
    private Integer article_id;
    private String title;
    private String category;
    private Integer likes;
    private Integer page_view;
    private String release_time;
    private String image;
    private List<ContentDTO> content;
    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getTitle() {
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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getPage_view() {
        return page_view;
    }

    public void setPage_view(Integer page_view) {
        this.page_view = page_view;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ContentDTO> getContent() {
        return content;
    }

    public void setContent(List<ContentDTO> content) {
        this.content = content;
    }

    public ArticlePage(Integer article_id, String title, String category, Integer likes, Integer page_view, String release_time, String image, List<ContentDTO> content) {
        this.article_id = article_id;
        this.title = title;
        this.category = category;
        this.likes = likes;
        this.page_view = page_view;
        this.release_time = release_time;
        this.image = image;
        this.content = content;
    }


    public static class ContentDTO {

        private Integer paragraph_id;
        private String text;
        private String audio;
        public ContentDTO(Integer paragraph_id, String text, String audio) {
            this.paragraph_id = paragraph_id;
            this.text = text;
            this.audio = audio;
        }

        public Integer getParagraph_id() {
            return paragraph_id;
        }

        public void setParagraph_id(Integer paragraph_id) {
            this.paragraph_id = paragraph_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

    }
}
