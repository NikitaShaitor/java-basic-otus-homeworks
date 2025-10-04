package ru.otus.java.basic;

import java.util.*;

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

    private static final Set<Position> managerPosition = new HashSet<>(List.of(
            MANAGER,
            DIRECTOR,
            BRANCH_DIRECTOR,
            SENIOR_MANAGER
    ));

    public boolean isManager(Person person) {
        if (person == null || person.position == null) {
            return false;
        }
        return managerPosition.contains(person.position);
    }

    public boolean isEmployee(long id) {
        Person person = findByID(id);
        if (person == null || person.position == null) {
            return false;
        }
        return !isManager(person);
    }
}


