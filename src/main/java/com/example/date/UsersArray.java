package com.example.date;

import com.example.domain.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UsersArray {
    ArrayList<User> users = new ArrayList<>();
    {
        users.add(new User("Pavel", "123","павел","лукашенко","александрович", new Date(104, Calendar.JUNE,10), User.Role.ADMIN, "pavellukasenko80@gmail.com"));
        users.add(new User("Igor", "1234", "игорь","соболев","петрович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Alexey", "1235", "алексей","носов","иванович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Anna", "1236", "анна","евтушенко","дмитриевна", new Date(104, Calendar.JUNE,10), User.Role.ADMIN, "pavellukasenko80@gmail.com"));
        users.add(new User("Vitaliy", "1237", "виталий","корбин","владимирович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Alexandr", "1238", "александр","иванов","павлович", new Date(104, Calendar.JUNE,10), User.Role.USER, "pavellukasenko80@gmail.com"));
        users.add(new User("Kirill", "1239", "кирилл","шмидт","никитич", new Date(104, Calendar.JUNE,10), User.Role.ADMIN, "pavellukasenko80@gmail.com"));
    }



    public ArrayList<User> getUsers() {
        return users;
    }

}
