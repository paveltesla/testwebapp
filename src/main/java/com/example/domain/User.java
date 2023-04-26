package com.example.domain;

import java.util.Date;

public class User {


    private int id;
    private String login;
    private String pass;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private String email;
    private Role role;
    public User(String login, String pass, String name, String surname, String patronymic, Date birthday, Role role, String email) {
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.email = email;
        this.role = role;
    }
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;

    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String login, String pass,Role role){
        this.login = login;
        this.pass = pass;
        this.role = role;
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

    public User(){

    }
    public User(String login, String pass, String email, Role role){
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", role=" + role +
                ", email='" + email + '\'' +
                '}';
    }

    public enum Role{
        USER, ADMIN,UNKNOWN
    }
}
