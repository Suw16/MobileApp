package com.suwat.myapplication;

public class Post {
    String headers;
    String details;

    public Post() {
    }

    public Post(String header, String details) {
        this.headers = header;
        this.details = details;
    }

    public String getHeader() {
        return headers;
    }

    public void setHeader(String header) {
        this.headers = header;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
