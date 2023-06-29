package com.mehr.SpringBootAdvanced.data;

import com.mehr.SpringBootAdvanced.beans.User;
import com.mehr.SpringBootAdvanced.beans.UserVersionOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao{

    static AddressDao addressDao = new AddressDaoImpl();

    private static List<User> usersList = new LinkedList<>();

    private static List<UserVersionOne> usersVersionOneList = new LinkedList<>();

    private static int count = 0;
    private static int versionOneCount = 0;

    static {

        usersList.add( new User(count,"Felipe", "Rodrigus Navarro", LocalDate.of(1968, 5,10), "fRodrigus", "FelipePw1"));
        usersList.add( new User(++count,"Sybille", "McDonald", LocalDate.of(1975, 5,15 ), "smacdonals", "SybilPw"));
        usersList.add( new User(++count,"Amy", "Osborn", LocalDate.of(2006, 6,27), "aosbort", "AmyPw4"));
        usersList.add( new User(++count,"Clair", "Viney", LocalDate.of(2015, 12,4), "cciney", "ClairPw56"));

        usersVersionOneList.add(new UserVersionOne(versionOneCount,addressDao.getAddressById(1),"Bruce", "Behrooz", "Roshanravan", LocalDate.of(1968, 5,10)));
        usersVersionOneList.add( new UserVersionOne(++versionOneCount, addressDao.getAddressById(0),"Laleh", "","Roshanravan", LocalDate.of(1975, 5,15)));
        usersVersionOneList.add( new UserVersionOne(++versionOneCount, addressDao.getAddressById(5),"Areya", "Chool-Talaa","Roshanravan", LocalDate.of(2006, 6,27)));
        usersVersionOneList.add( new UserVersionOne(++versionOneCount,addressDao.getAddressById(4), "Atoosa", "AtiMati","Roshanravan", LocalDate.of(2015, 12,4)));

    }

    public List<User> getAllUsers(){
        return usersList;
    }

    public List<UserVersionOne> getAllVersionOneUsers(){
        return usersVersionOneList;
    }

    public int addUserVersionOne(UserVersionOne userVersionOneIn){
        userVersionOneIn.setUserId(++versionOneCount);
        if(userVersionOneIn.getAddressId()>versionOneCount){
            throw (new RuntimeException());
        }
        usersVersionOneList.add(userVersionOneIn);
        return userVersionOneIn.getUserId();

    }

    public int addUser(User userIn){
        userIn.setUserId(++count);
        usersList.add(userIn);
        return userIn.getUserId();
    }

    /**
     * The orElse methods comes to action when now
     * value is found
     * @param userIdIn
     * @return
     */

    public User getUserById(int userIdIn){
        return  usersList.stream().filter(user ->user.getUserId() == userIdIn).findFirst().orElse(null);
    }

    public UserVersionOne getUserVersionOneById(int userVersionOneIdIn){
        return  usersVersionOneList.stream().filter(userVersionOne -> userVersionOne.getUserId()== userVersionOneIdIn).findFirst().orElse(null);
    }

    /**
     *
     * Deletes the user is the Id  is
     * same as the id passed into the method
     * @param userIdIn
     */

    public void deleteUserById(int userIdIn){
         usersList.removeIf(user ->user.getUserId() == userIdIn);
    }


    public void deleteUserVersionOne(int userVersionOneIdIn){
        usersVersionOneList.removeIf(userVersionOne ->userVersionOne.getUserId() == userVersionOneIdIn);
    }

}
