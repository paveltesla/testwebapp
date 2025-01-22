package com.example.dao;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {

    ArrayList<User> getAll();

    User getUserByLogin(String login);

}
