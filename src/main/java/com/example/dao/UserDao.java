package com.example.dao;

import com.example.domain.Role;
import com.example.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserDao {

    ArrayList<User> getAll();

    User getUserByLogin(String login);

    void addUser(String login, String pass, String name, int age, String birthday, ArrayList<Role> role);

    void delete(String login);

    void editPass(User user, String nPassRep);

    void editUser(String login, String name, int age, String birthday, float salary, ArrayList<Role> roles);

    void regUser(String login, String name, int age, String pass, ArrayList<Role> roles);

}
