package com.example.achoholdetection;


import java.io.Serializable;

public class myClass implements Serializable {
    public String message;
    public int number;
    public Boolean truth;

    public myClass(){
    }
    public myClass(int number)
    {
        //this.message=message;
        this.number=number;
        //this.truth=truth;
    }
    public int getNumber()
    {
        return number;
    }
}
