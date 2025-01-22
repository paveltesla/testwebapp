package com.example.domain;

import java.util.Date;
import java.util.List;

public class User {


    private int id;
    private String login;
    private String pass;
    private String name;
    private Date birthday;
    private int age;
    private List<Role> role;
    private float salary;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(String login, String pass, List<Role> role) {
        this.login = login;
        this.pass = pass;
        this.role = role;
    }

    public User(String login, String password, String name, int age, Date birthday, float salary) {
        this.pass = password;
        this.name = name;
        this.age = age;
        this.login = login;
        this.salary = salary;
        this.birthday = birthday;
    }

    public User(String login, String pass, String name, int age) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", role=" + role +
                '}';
    }

    public boolean hasRole(String roles) {
        String strRoles = role.toString();
        return strRoles.contains(roles);
    }
}
