package com.example.dao;

import com.example.date.UsersArray;
import com.example.domain.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class UserDaoImplement implements UserDao {

    UsersArray users = new UsersArray();


    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.getUsers().get((int) id));
    }

    @Override
    public ArrayList<User> getAll() {
        return users.getUsers();
    }


    @Override
    public void addUser(String login, String pass, String name, String surname, String patronymic, Date birthday, User.Role role, String email) {
        users.getUsers().add(new User(login,pass,name,surname,patronymic,birthday,role,email));
    }


    public User getLoginPass(final String login, final String pass){
        User result = new User();
        for (User user: users.getUsers()){
            if(user.getLogin().equals(login)&&user.getPass().equals(pass)){
                result = user;
            }
        }
        return result;
    }

    @Override
    public User getLogin(String login) {
        User result = new User();
        for (User u:users.getUsers()) {
            if (u.getLogin().equals(login)){
                result = u;
            }
        }
        return result;
    }

}
