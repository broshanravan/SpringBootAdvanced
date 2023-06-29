package com.mehr.SpringBootAdvanced.controllers;

import com.mehr.SpringBootAdvanced.beans.User;
import com.mehr.SpringBootAdvanced.beans.UserVersionOne;
import com.mehr.SpringBootAdvanced.data.UserDao;
import com.mehr.SpringBootAdvanced.data.UserDaoImpl;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/")
public class VersioningUserController {

    UserDao userDao = new UserDaoImpl();

    @GetMapping(path = "/getAllUsers")
    public List<User> getAllUsers(){
        List<User>  userList = userDao. getAllUsers();
        return userList;
    }
/*
    @GetMapping(path = "/getUser/{userId}")
    public User getUserById(@PathVariable int userId){
        User user = userDao.getUserById(userId);
        return user;
    }

    @PostMapping(path = "/addUser")
    public int addUser(@RequestBody User user){
        int userId = userDao.addUser(user);
        return userId;
    }
*/
    @GetMapping(path = "/v1/getAllUsers")
    public List<UserVersionOne> getAllV1Users(){
        List<UserVersionOne>  userVersionOneList = userDao. getAllVersionOneUsers();
        return userVersionOneList;
    }

    /**
     * Passing parameters through
     * hardcoding via URL
     * @param userId
     * @return
     */
    @GetMapping(path = "v1/getUser/{userId}")
    public UserVersionOne getUsVersionOneByUrlId(@PathVariable int userId){
        UserVersionOne userVersionOne = userDao.getUserVersionOneById(userId);
        return userVersionOne;
    }

    @PostMapping(path = "v1/addUser")
    public int addUserVersionOne(@RequestBody UserVersionOne userVersionOneIn){
        int userId = userDao.addUserVersionOne(userVersionOneIn);
        return userId;
    }

    /**
     * Passing parameters through
     * request parameter
     * @param userId
     * @return
     */
    @GetMapping(path="/getUser/{userId}", params="version=1")
    public EntityModel<UserVersionOne> getUserVersionOneByRequestParamId(@PathVariable int userId){
        UserVersionOne userVersionOne = userDao.getUserVersionOneById(userId);
        EntityModel<UserVersionOne> userEntityModel = EntityModel.of(userVersionOne);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());

        userEntityModel.add(link.withRel("get-All-Users"));
        return userEntityModel;
    }

    /**
     * Passing parameters through
     * header
     * @param userId
     * @return
     */
    @GetMapping(path="/getUser/{userId}", headers="X-API-Version=1")
    public UserVersionOne getUserVersionOneByHeaderParam(@PathVariable int userId){
        UserVersionOne userVersionOne = userDao.getUserVersionOneById(userId);
        return userVersionOne;
    }

    /**
     * Passing parameters through
     * header
     * @param userId
     * @return
     */
    @GetMapping(path="/getUser/header/{userId}", headers="X-API-Version=2")
    public User getUserByHeaderParam(@PathVariable int userId){
        User user = userDao.getUserById(userId);
        return user;
    }

    /***
     * The parameter goes to header params as
     * 'X-API-VERSION' header Parameter
     * @param userId
     * @return
     */
    @GetMapping(path="getUser/headers/{userId}" , headers="versionOne")
    public UserVersionOne getUserVersionOneByHeader(@PathVariable int userId){
        UserVersionOne userVersionOne = userDao.getUserVersionOneById(userId);
        return userVersionOne;
    }

    /**
     *  for media type versioning,
     *  the parameter goes to header params
     *  as 'ACCEPT' header Parameter
     * The header is in form of url
     * @param userId
     * @return
     */

    @GetMapping(path="getUser/accept/{userId}" , produces="application/vnd.SpringBootAdvanced.app-V1+json")
    public User getUserMediaType(@PathVariable int userId){
        User user = userDao.getUserById(userId);
        return user;
    }

    @GetMapping(path="getUser/accept/{userId}" , produces="application/vnd.SpringBootAdvanced.app-V2+json")
    public UserVersionOne getUserVersionOneByMediaType(@PathVariable int userId){
        UserVersionOne userVersionOne = userDao.getUserVersionOneById(userId);
        return userVersionOne;
    }



}