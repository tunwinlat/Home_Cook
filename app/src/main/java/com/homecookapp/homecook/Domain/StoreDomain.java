package com.homecookapp.homecook.Domain;

public class StoreDomain {

    public StoreDomain(String pic, String title, String type, String location, String rating) {
        this.pic = pic;
        this.title = title;
        this.type = type;
        this.location = location;
        this.rating = rating;
    }

    private String pic;
    private String title;
    private String type;
    private String location;
    private String rating;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
