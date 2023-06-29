package com.mehr.SpringBootAdvanced.beans;

import jakarta.validation.constraints.Size;
//import lombok.*;

//@Setter
//@Getter
//@ToString
//@NoArgsConstructor
public class Address {

    private int addressId;

    @Size(min=2,  message="House number (name) should be at least1 character")
    private String houseNameNumber;

    @Size(min=5, max=25, message="Address Line should be between 2 an d15 characters")
    private String AddressLineOne;

    private String addressLineTwo;

    @Size(min=7, max=9, message="postCode should be between 7 and 9 characters")
    private String postCode;

    @Size(min=7, message="Town name should be at least 7 characters")
    private String postTown;


    public Address(int addressId, String houseNameNumber,String AddressLineOne,
                   String addressLineTwo,String postCode,String postTown){
        this.addressId = addressId;
        this.houseNameNumber = houseNameNumber;
        this.AddressLineOne = AddressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.postCode = postCode;
        this.postTown = postTown;

    }

    public Address(int addressId, String houseNameNumber,String AddressLineOne,
                   String postCode,String postTown){
        this.addressId = addressId;
        this.houseNameNumber = houseNameNumber;
        this.AddressLineOne = AddressLineOne;
        this.postCode = postCode;
        this.postTown = postTown;

    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getHouseNameNumber() {
        return houseNameNumber;
    }

    public void setHouseNameNumber(String houseNameNumber) {
        this.houseNameNumber = houseNameNumber;
    }

    public String getAddressLineOne() {
        return AddressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        AddressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostTown() {
        return postTown;
    }

    public void setPostTown(String postTown) {
        this.postTown = postTown;
    }
}
