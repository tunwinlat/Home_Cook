package com.homecookapp.homecook.Domain;

public class ProductDomain {

    private String title;
    private String pic;
    private String type;
    private String price;
    private String stock;

    public ProductDomain(String title, String pic, String type, String price, String stock) {
        this.title = title;
        this.pic = pic;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
