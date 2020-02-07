package com.kisicsoft.harutest;

public class Cardviewitem {

    public String imageview;
    public String title;
    public String star;
    public String review;
    public String distance;

    public Cardviewitem() {
    }

    public String getImageview() {
        return imageview;
    }

    public void setImageview(String imageview) {
        this.imageview = imageview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Cardviewitem(String imageview, String title, String star, String review, String distance) {
        this.imageview = imageview;
        this.title = title;
        this.star = star;
        this.review = review;
        this.distance = distance;
    }
}
