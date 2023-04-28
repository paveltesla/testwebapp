package com.example.dao;

public class UserFactory {
    public UserDao create(String TypeDao){
        switch (TypeDao){
            case "UserDaoImplement" : return UserDaoSingleton.getInstance().getValue();
            default: return null;
        }
    }
}
