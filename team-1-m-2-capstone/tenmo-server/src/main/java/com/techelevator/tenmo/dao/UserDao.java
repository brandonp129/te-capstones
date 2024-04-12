package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.RegisterUserDto;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Users;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    //test
    List<Users> getListUsers(int userId);

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
}
