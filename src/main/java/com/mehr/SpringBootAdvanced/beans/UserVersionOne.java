package com.mehr.SpringBootAdvanced.beans;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class UserVersionOne {

    private int userId;

    private int addressId;

    private Address address;

    @Size(min=2, max=15, message="First name should be between 2 an 15 characters")
    private String firstName;

    private String middleName;

    @Size(min=2, max=15, message="surname should be between 2 an 15 characters")
    private String surname;

    @Past(message = "Data of Birth should always e in the past")
    private LocalDate dateOfBrith;

    public UserVersionOne(){

    }
    public UserVersionOne(int userId, Address addressIn, String firstName, String middleName, String surname, LocalDate dateOfBrith) {
        this.userId = userId;
        this.address = addressIn;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.dateOfBrith = dateOfBrith;
    }

    public UserVersionOne(int addressId, String firstName, String middleName, String surname, LocalDate dateOfBrith) {
        this.firstName = firstName;
        this.addressId =addressId;
        this.middleName = middleName;
        this.surname = surname;
        this.dateOfBrith = dateOfBrith;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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


}
