package com.example.popularmoviesapp.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trailer")
public class Trailer {

    private int mMovieId;

    @PrimaryKey
    @NonNull
    private String mId;
    private String mIso_639_1;
    private String mIso_3166_1;
    private String mKey;
    private String mName;
    private String mSite;
    private String mSize;
    private String mType;
    private String mYoutubeLink;
    private String YOUTUBE_BASE = "https://www.youtube.com/watch?v=";

    public Trailer(int movieId, String id, String iso_639_1, String iso_3166_1, String key, String name, String site, String size, String type) {
        mId = id;
        mIso_639_1 = iso_639_1;
        mIso_3166_1 = iso_3166_1;
        mKey = key;
        mName = name;
        mSite = site;
        mSize = size;
        mType = type;
        mYoutubeLink = YOUTUBE_BASE + key;
        mMovieId = movieId;
    }

    public int getMovieId() {
        return this.mMovieId;
    }

    public void setMovieId(int movieId) {
        this.mMovieId = movieId;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getYoutubeLink() {
        return mYoutubeLink;
    }

    public void setYoutubeLink(String mYoutubeLink) {
        this.mYoutubeLink = mYoutubeLink;
    }

    public String getIso_639_1() {
        return mIso_639_1;
    }

    public void setIso_639_1(String mIso_639_1) {
        this.mIso_639_1 = mIso_639_1;
    }

    public String getIso_3166_1() {
        return mIso_3166_1;
    }

    public void setIso_3166_1(String mIso_3166_1) {
        this.mIso_3166_1 = mIso_3166_1;
    }

    public String getKey() {
        return mKey;
    }

    public void setKey(String mKey) {
        this.mKey = mKey;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getSite() {
        return mSite;
    }

    public void setSite(String mSite) {
        this.mSite = mSite;
    }

    public String getSize() {
        return mSize;
    }

    public void setSize(String mSize) {
        this.mSize = mSize;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }

    public String getYOUTUBE_BASE() {
        return YOUTUBE_BASE;
    }

    public void setYOUTUBE_BASE(String YOUTUBE_BASE) {
        this.YOUTUBE_BASE = YOUTUBE_BASE;
    }
}
