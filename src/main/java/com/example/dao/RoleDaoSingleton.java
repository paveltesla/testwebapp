package com.example.dao;

public class RoleDaoSingleton {
    public RoleDao value;
    private static RoleDaoSingleton instance;

    public RoleDaoSingleton() {
        this.value = new RoleDaoImplement();
    }

    public static RoleDaoSingleton getInstance(){
        if(instance == null){
            instance = new RoleDaoSingleton();
        }
        return instance;
    }

    public RoleDao getValue() {
        return value;
    }
}
