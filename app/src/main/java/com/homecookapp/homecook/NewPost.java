package com.homecookapp.homecook;

public class NewPost {

    private String name, type, quantity, description, uri;

    public NewPost(){

    }


    public NewPost(String _name, String _type, String _quantity, String _description, String _uri){

        this.name = _name;
        this.type = _type;
        this.quantity = _quantity;
        this.description = _description;
        this.uri = _uri;
    }

    public String getName(){
        return name;
    }

    public String getType() {return type;}

    public String getQuantity() {return quantity;}

    public String getDescription() {return description;}

    public String getUri() {return uri;}
}
