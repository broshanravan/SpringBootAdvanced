package com.mehr.SpringBootAdvanced.data;

import com.mehr.SpringBootAdvanced.beans.Address;

public interface AddressDao {

    public Address getAddressById(int addressId);

    public int addAddress(Address AddressIn);

    public void deleteAddress(int addressId);

}
