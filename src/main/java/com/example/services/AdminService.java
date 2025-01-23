package com.example.services;

import com.example.domain.Role;
import com.example.domain.User;

import java.util.ArrayList;

public interface AdminService {

    boolean userIsExist(String login, String pass);

    User getUserByLogin(String login);

    void regUser(String login, String name, int i, String pass, ArrayList<Role> roles);

    void addUser(String login, String pass, String name, int i, String birthdayStr, ArrayList<Role> roles);

    void editPass(User userByLogin, String nPassRep);

    void editUser(String login, String name, int i, String birthdayStr, float v, ArrayList<Role> roles);

    void delete(String login);
}
