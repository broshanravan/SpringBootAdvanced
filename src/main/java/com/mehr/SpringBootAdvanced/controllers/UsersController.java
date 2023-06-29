package com.mehr.SpringBootAdvanced.controllers;

import com.mehr.SpringBootAdvanced.beans.User;
import com.mehr.SpringBootAdvanced.data.UserDao;
import com.mehr.SpringBootAdvanced.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@Validated
@RequestMapping("/api")
public class UsersController {

    @Autowired
    UserDao userDao;

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    /**
     * The status code for delete should be
     * 200
     * @param userId
     */

    @DeleteMapping("/deleteUser/{userId}")
    public void deleteUserById(@PathVariable int userId){
        userDao.deleteUserById(userId);
    }

    /**
     * The following method has now been enhanced
     * by adding HATEOAS
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public EntityModel<User> getUserById(@PathVariable int userId){
        User user = userDao.getUserById(userId);
        if(user == null){
            throw new UserNotFoundException("User with id " + userId + " not found" );
        }

        EntityModel<User> userEntityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());

        userEntityModel.add(link.withRel("get-All-Users"));

        return userEntityModel;
    }

    /**
     * including the correct status of 201
     * for created and the URI of accessing the
     * newly created
     * @param user
     */
    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) throws URISyntaxException {
        int userId= userDao.addUser(user);
        URI location =  createUri("http",
                "localhost",
                8080,
                "/user/" + userId,
                null,
                null
       );

       return ResponseEntity.created(location).build();
    }

    private URI createUri(String protocol,String host,int port,String path,String auth,String fragment ) throws URISyntaxException {
       return new URI(protocol, auth, host, port, path, null, fragment);
    }


}
