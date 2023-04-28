package com.example.dao;

public class UserDaoSingleton {
    public UserDao value;
    private static UserDaoSingleton instance;

    public UserDaoSingleton() {
        this.value = new UserDaoImplement();
    }

    public static UserDaoSingleton getInstance(){
        if(instance == null){
            instance = new UserDaoSingleton();
        }
        return instance;
    }

    public UserDao getValue() {
        return value;
    }
}
