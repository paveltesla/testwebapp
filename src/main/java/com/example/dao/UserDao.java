package com.example.dao;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.ArrayList;

public interface UserDao {

    ArrayList<User> getAll();

    void addUser(String login, String pass, String name, int age, String birthday, ArrayList<Role> role);

    User getUserByLoginPass(final String login, final String pass);

    User getUserByLogin(final String login);

}
