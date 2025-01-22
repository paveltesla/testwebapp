package com.example.dao;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {

    ArrayList<User> getAll();

    void addUser(String login, String pass, String name, int age, String birthday, ArrayList<Role> role);

    User getUserByLogin(String login);

    //List<User> getAllUsers();

   // void addUser(User user);

    //void editUser(User user);

    //void deleteUser(int userId);

}
