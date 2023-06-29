package com.mehr.SpringBootAdvanced.controllers;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.mehr.SpringBootAdvanced.beans.FilteredUser;
import com.mehr.SpringBootAdvanced.beans.User;
import com.mehr.SpringBootAdvanced.data.UserDao;
import com.mehr.SpringBootAdvanced.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UserFilteringController {


    @Autowired
    UserDao userDao;


    /**
     * The following method has now been enhanced
     * by adding HATEOAS to display the link to all users
     * and filtered for not returning username
     * @param userId
     * @return
     */

    @GetMapping("/unFilter/user/{userId}")
    public MappingJacksonValue getUserByIdUnFilter(@PathVariable int userId){
        FilteredUser filteredUser = new FilteredUser(userDao.getUserById(userId));
        if(filteredUser == null){
            throw new UserNotFoundException("User with id " + userId + " not found" );
        }

        //creating entityModel
        EntityModel<FilteredUser> userEntityModel = EntityModel.of(filteredUser);

        /* HATEOAS to add links for all users */
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUserUnFilter());
        userEntityModel.add(link.withRel("get-All-usernameFiltered-Users"));

        //adding entityModer to mapping Jackson value (or wrapping mappingJacksonValue arrount it )
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userEntityModel);

        /* dynamic filtering code
        * creating filter*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("username");
        SimpleBeanPropertyFilter filter2 = SimpleBeanPropertyFilter.serializeAllExcept("userId");

        //adding filter to filter provider which may have many filters in it
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",filter).addFilter("userFilter",filter2);


        //adding filter provider to wrapper class
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/noFilter/user/{userId}")
    public MappingJacksonValue getUserByIdNoFilter(@PathVariable int userId){
        FilteredUser filteredUser = new FilteredUser(userDao.getUserById(userId));
        if(filteredUser == null){
            throw new UserNotFoundException("User with id " + userId + " not found" );
        }

        /* dynamic filtering code*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();

        /* HATEOAS to add links for all users */
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUserUnFilter());
        EntityModel<FilteredUser> userEntityModel = EntityModel.of(filteredUser);
        userEntityModel.add(link.withRel("get-All-usernameFiltered-Users"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userEntityModel);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",filter);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/pwFilter/user/{userId}")
    public MappingJacksonValue getUserByIdPwFilter(@PathVariable int userId){
        FilteredUser filteredUser = new FilteredUser(userDao.getUserById(userId));
        if(filteredUser == null){
            throw new UserNotFoundException("User with id " + userId + " not found" );
        }

        /* dynamic filtering code*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","firstName", "surname", "dateOfBrith", "username");

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUserPwFilter());

        /*HATEOAS to add links for all users*/
        EntityModel<FilteredUser> userEntityModel = EntityModel.of(filteredUser);
        userEntityModel.add(link.withRel("get-All-passwordFiltered-Users"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userEntityModel);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",filter);
        mappingJacksonValue.setFilters(filterProvider);




        return mappingJacksonValue;
    }

    @GetMapping("/noFilter/allUsers")
    public MappingJacksonValue getAllUserNoFilter(){
        List<FilteredUser> filteredUsers = filterUsers(userDao.getAllUsers());

        if(filteredUsers == null || filteredUsers.isEmpty()){
            throw new UserNotFoundException("No filteredUsers found");
        }

        /* dynamic filtering code*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteredUsers);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",filter);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }


    @GetMapping("/pwFilter/allUsers")
    public MappingJacksonValue getAllUserPwFilter(){
        List<FilteredUser> filteredUsers = filterUsers(userDao.getAllUsers());

        if(filteredUsers == null || filteredUsers.isEmpty()){
            throw new UserNotFoundException("No filteredUsers found");
        }

        /* dynamic filtering code*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("userId","firstName", "surname", "dateOfBrith", "username");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteredUsers);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",filter);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }

    @GetMapping("/unFilter/allUsers")
    public MappingJacksonValue getAllUserUnFilter(){
        List<FilteredUser> FilteredUsers = filterUsers(userDao.getAllUsers());
        if(FilteredUsers == null || FilteredUsers.isEmpty()){
            throw new UserNotFoundException("No FilteredUsers found");
        }

        /* dynamic filtering code*/
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName", "surname", "dateOfBrith", "password");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(FilteredUsers);
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("userFilter",filter);
        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;
    }


    private List<FilteredUser> filterUsers(List<User> users){
        List<FilteredUser> filteredUsers = new LinkedList<>();
        users.stream().map(user ->new FilteredUser(user)).forEach(filteredUser -> filteredUsers.add(filteredUser));
        return filteredUsers;

    }



}
