package com.example.domain;

public class Role {
    private String role;
    private int id;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role(String role) {
        this.role = role;
    }
    public Role(int id, String role) {
        this.role = role;
        this.id = id;
    }

    public Role(){}

    @Override
    public String toString() {
        return role;
    }
}
