package com.itonemm.blogprojectonline;

public class NewsModel {

    String imageUrl;
    String newsUrl;
    String author;
    String publishedDate;
    String title;

    public NewsModel(String imageUrl, String newsUrl, String author, String publishedDate, String title) {
        this.imageUrl = imageUrl;
        this.newsUrl = newsUrl;
        this.author = author;
        this.publishedDate = publishedDate;
        this.title = title;
    }

    public NewsModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
