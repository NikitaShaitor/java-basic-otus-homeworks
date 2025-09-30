package ru.otus.java.basic;

import java.util.*;

public class PhoneBook {
    private Map<String, List<String>> phoneBook = new HashMap<>();

    public void add(String fio, String phoneNumber) {
        List<String> phones = phoneBook.computeIfAbsent(fio, k -> new ArrayList<>());
        phones.add(phoneNumber);
    }

    public List<String> find(String fio) {
        return phoneBook.getOrDefault(fio, Collections.emptyList());
    }

    public boolean containsPhoneNumber(String phoneNumber) {
        for (List<String> phones : phoneBook.values()) {
            if (phones.contains(phoneNumber)) {
                return true;
            }
        }
        return false;
    }
}
