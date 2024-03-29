package ru.job4j;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private int children;
    private Calendar birthday;


    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar cal = new GregorianCalendar(2010, Calendar.JULY, 15);
        User user1 = new User("Alex", 12, cal);
        User user2 = new User("Alex", 12, cal);
        Map<User, Object> myHashMap = new HashMap<>();
        myHashMap.put(user1, new Object());
        myHashMap.put(user2, new Object());
    }
}