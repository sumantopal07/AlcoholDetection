package com.example.achoholdetection;


import java.io.Serializable;

public class UserProfile implements Serializable {
    public String userAge;
    public String userEmail;
    public String userName,contactDetails;

    public UserProfile(){
    }

    public UserProfile(String userAge, String userEmail, String userName,String contactDetails) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
        this.contactDetails=contactDetails;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }
    public String getContactDetails() {
        return contactDetails;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
