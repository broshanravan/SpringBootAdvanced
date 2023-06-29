package com.mehr.SpringBootAdvanced.controllers;

import com.mehr.SpringBootAdvanced.beans.Address;
import com.mehr.SpringBootAdvanced.data.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import com.mehr.SpringBootAdvanced.data.AddressDao;

@RestController
@RequestMapping("/api/")
public class AddressController {

    @Autowired
    AddressDao addressDao;

    @GetMapping(path= "address/{addressId}")
    public Address hetAddressById(@PathVariable int addressId){
        Address address = addressDao.getAddressById(addressId);

        return address;
    }

    @PostMapping(path= "address/{addressId}")
    public ResponseEntity<Address> saveAddress(Address addressIn) throws URISyntaxException{
        int addressId = addressDao.addAddress(addressIn);
        URI location =  createUri("http",
                "localhost",
                8080,
                "/user/" + addressId,
                null,
                null
        );

        return ResponseEntity.created(location).build();
    }

    private URI createUri(String protocol,String host,int port,String path,String auth,String fragment ) throws URISyntaxException {
        return new URI(protocol, auth, host, port, path, null, fragment);
    }
}
