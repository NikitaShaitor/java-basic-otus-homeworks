package ru.otus.java.basic;

import java.util.HashMap;
import java.util.Map;

import static ru.otus.java.basic.Position.*;

public class PersonDataBase {
    private Map<Long, Person> persons = new HashMap<>();



    public void add(Person person) {
        if (person != null && person.id != null) {
            this.persons.put(person.id, person);
        }
    }

    public Person findByID(Long id) {
        return persons.get(id);
    }

    public boolean isManager(Person person) {
        if (person == null || person.position == null) {
            return false;
        }

        switch (person.position) {
            case MANAGER:
            case DIRECTOR:
            case BRANCH_DIRECTOR:
            case SENIOR_MANAGER:
                return true;
            default:
                return false;
        }
    }

    public boolean isEmployee(long id) {
        Person person = findByID(id);
        if (person == null || person.position == null) {
            return false;
        }
        return !isManager(person);
    }
}


