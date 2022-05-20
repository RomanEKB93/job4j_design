package ru.job4j.map;

import java.util.*;

public class HashMapUsage {
    public static void main(String[] args) {
        User user1 = new User("Gleb Zheglov", 0,
                new GregorianCalendar(1919, Calendar.JULY, 4));
        User user2 = new User("Gleb Zheglov", 0,
                new GregorianCalendar(1919, Calendar.JULY, 4));
        Map<User, Object> trialMap = new HashMap<>();
        trialMap.put(user1, new Object());
        trialMap.put(user2, new Object());
        System.out.println(trialMap);
    }
}
