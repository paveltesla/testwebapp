package com.example.services;

import com.example.domain.Role;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dao.*;

import java.util.ArrayList;

@Service
public class AdminServiceImplement implements AdminService {

//    private static volatile AdminServiceImplement instance;
//    private AdminServiceImplement() {
//    }
//    public static AdminServiceImplement getInstance() {
//        if (instance == null) {
//            synchronized (AdminServiceImplement.class) {
//                if (instance == null) {
//                    instance = new AdminServiceImplement();
//                }
//            }
//        }
//        return instance;
//    }

    @Autowired private UserDao userDao;


    public boolean userIsExist(String login, String pass) {
        boolean result = false;
        for (User user : userDao.getAll()) {
            if (user.getLogin().equals(login) && user.getPass().equals(pass)) {
                result = true;
                break;
            }
        }
        return result;
    }


    public void addUser(String login, String pass, String name, int age, String birthday, ArrayList<Role> role) {
        userDao.addUser(login, pass, name, age, birthday, role);
    }


    public void delete(String login) {
        userDao.delete(login);
    }


    public void editPass(User user, String nPassRep) {
        userDao.editPass(user, nPassRep);
    }


    public void editUser(String login, String name, int age, String birthday, float salary, ArrayList<Role> roles) {
        userDao.editUser(login, name, age, birthday, salary, roles);
    }


    public void regUser(String login, String name, int age, String pass, ArrayList<Role> roles) {
        userDao.regUser(login, name, age, pass, roles);
    }

    public User getUserByLogin(String login) {
        userDao.getUserByLogin(login);
        return null;
    }
}
