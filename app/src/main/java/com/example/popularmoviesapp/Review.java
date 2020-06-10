package com.example.popularmoviesapp;

public class Review {

    private String mAuthor;
    private String mContent;
    private String mId;
    private String mUrl;


    public Review(String author, String content, String id, String url){
        mAuthor = author;
        mContent = content;
        mId = id;
        mUrl = url;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

}
