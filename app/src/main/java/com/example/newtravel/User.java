package com.example.newtravel;

public class User {
    public String Name;
    public String Phone;
    public String Nif;
    public String Address;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name, String Pho,String Nnf,String Add) {
        this.Name = name;
        this.Phone = Pho;
        this.Nif=Nnf;
        this.Address=Add;
    }
}
