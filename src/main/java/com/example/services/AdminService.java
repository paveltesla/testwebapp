package com.example.services;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.ArrayList;
import java.util.Date;

public interface AdminService {

    boolean userIsExist(String login, String pass);

    User getUserByLogin(String login);

    void regUser(String login, String name, int age, String pass, ArrayList<Role> roles);

    void addUser(String login, String pass, String name, int age, String birthday, ArrayList<Role> roles);

    void editPass(User userByLogin, String nPassRep);

    void editUser(String login, String name, int age, String birthdayStr, float salary, ArrayList<Role> roles);

    void delete(String login);
}
