package com.example.services;

public class ServiceDaoSingleton {
    public ServiceDao value;
    private static ServiceDaoSingleton instance;

    public ServiceDaoSingleton() {
        this.value = new ServiceDaoImplement();
    }

    public static ServiceDaoSingleton getInstance(){
        if(instance == null){
            instance = new ServiceDaoSingleton();
        }
        return instance;
    }

    public ServiceDao getValue() {
        return value;
    }
}
