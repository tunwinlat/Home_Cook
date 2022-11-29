package com.homecookapp.homecook;

public class NewPost {

    private String name, accountStatus;
    private boolean seller;

    public NewPost(String _name, String _accountStatus, boolean _seller){

        this.name = _name;
        this.accountStatus = _accountStatus;
        this.seller = _seller;
    }

    public String getName(){
        return name;
    }

    public String getAccountStatus() { return accountStatus; }

    public boolean getSeller() { return  seller; }
}
