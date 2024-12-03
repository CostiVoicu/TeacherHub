package com.example.teacherhub.model;

import java.util.Comparator;

public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String userType;
    private String email;
    private String password;

    public static Comparator<User> getAlphabeticalComparator() {
        return Comparator
                .comparing(User::getLastName)
                .thenComparing(User::getFirstName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

