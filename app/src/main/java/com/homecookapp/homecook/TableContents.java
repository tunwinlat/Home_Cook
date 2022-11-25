package com.homecookapp.homecook;

public class TableContents {

    private String ingredients;

    public TableContents (String _keyName){
        this.ingredients = _keyName;
    }


    public String getIngredients(){
        return this.ingredients;
    }
}
