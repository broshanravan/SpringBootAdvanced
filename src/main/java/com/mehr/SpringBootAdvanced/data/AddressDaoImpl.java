package com.mehr.SpringBootAdvanced.data;

import com.mehr.SpringBootAdvanced.beans.Address;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class AddressDaoImpl implements AddressDao{

    public static List<Address> addressList = new LinkedList<Address>();

    private static int addressIdCount = 0;
    static{

        addressList.add(new Address(addressIdCount++,"North London YMCA","184 Tottenham Line","N8 8SJ","London"));
        addressList.add(new Address(addressIdCount++,"39","ParkHurst Road","N8 8JQ","London"));
        addressList.add(new Address(addressIdCount++,"47", "Northumberland Park",
                "N17 0QA","London"));
        addressList.add(new Address(addressIdCount++,"64","Hill Lane", "Shirley","SO1 3AS","Southampton"));
        addressList.add(new Address(addressIdCount++,"32","Carlton Road","SO34 5AS","Southampton"));
        addressList.add(new Address(addressIdCount++,"2A","Thornbury  Avenue","SO34 5AS","Southampton"));
        addressList.add(new Address(addressIdCount++,"46B","Hill Lane","SO34 5AS","Southampton"));
        addressList.add(new Address(addressIdCount++,"25","Gainsford Road","SO19 7AS","Southampton"));
        addressList.add(new Address(addressIdCount++,"18","Chisholm Close","Lordshill","SO16 8GU","Southampton"));
        addressList.add(new Address(addressIdCount++,"2","Henlow Drive","GL2 2DD","Gloucester"));

    }

    public Address getAddressById(int addressId){
        Address emptyAddress = new Address(0,"","","","","");
        return addressList.stream().filter(address -> address.getAddressId() == addressId).findFirst().orElse(emptyAddress);

    }
    public int addAddress(Address addressIn){
        int addressId = addressIdCount ++;
        addressIn.setAddressId(addressId);
        addressList.add(addressIn);

        return addressId;

    }
    public void deleteAddress(int addressId){
        addressList.removeIf(address -> address.getAddressId() == addressId);

    }
}
