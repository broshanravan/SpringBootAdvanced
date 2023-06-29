package com.mehr.SpringBootAdvanced.beans;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;


public class User implements Serializable {

    private int userId;

    @Size(min=2, max=15, message="First name should be between 2 and 15 characters")
    private String firstName;

    @Size(min=2, max=15, message="surname should be between 2 and 15 characters")
    private String surname;

    @Past(message = "Data of Birth should always be in the past")
    private LocalDate dateOfBrith;

    @Size(min=2, max=15, message="username should be between 2 and 15 characters")
    private String username;

    @Size(min=2, max=15, message="password should be between 2 and 15 characters")
    private String password;


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", dateOfBrith=" + dateOfBrith +
                '}';
    }

    public User(){

    }

    public User(int userId, String firstName, String surname, LocalDate dateOfBrith, String username, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBrith = dateOfBrith;
        this.username = username;
        this.password = password;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBrith() {
        return dateOfBrith;
    }

    public void setDateOfBrith(LocalDate dateOfBrith) {
        this.dateOfBrith = dateOfBrith;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
