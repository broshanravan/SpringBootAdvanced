package com.mehr.SpringBootAdvanced.data;

import com.mehr.SpringBootAdvanced.beans.User;
import com.mehr.SpringBootAdvanced.beans.UserVersionOne;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public int addUser(User userIn);

    public void deleteUserById(int userIdIn);

    public User getUserById(int userIdIn);

    public List<UserVersionOne> getAllVersionOneUsers();


    /**
     * Static:
     * URI versioning
     */

    public UserVersionOne getUserVersionOneById(int userVersionOneIdIn);

    public int addUserVersionOne(UserVersionOne userVersionOneIn) ;

    public void deleteUserVersionOne(int userVersionOneIdIn);



    /**
     * dynamic:
     * Request Parameter
     * Versioning
     */



    /**
     * Dynamic:
     * Request Header
     * Versioning
     */


}
