package com.example.englishapp.databean;

public class MyPicture {
    public String getUrl_pie() {
        return url_pie;
    }

    public void setUrl_pie(String url_pie) {
        this.url_pie = url_pie;
    }

    public String getUrl_line() {
        return url_line;
    }

    public void setUrl_line(String url_line) {
        this.url_line = url_line;
    }

    public MyPicture(String url_pie, String url_line) {
        this.url_pie = url_pie;
        this.url_line = url_line;
    }

    private String url_pie;
    private String url_line;
}
