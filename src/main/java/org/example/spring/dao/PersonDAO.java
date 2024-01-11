package org.example.spring.dao;

import org.example.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 1;
    private final List<Person> people = new ArrayList<>();

    {
        people.add(new Person(PEOPLE_COUNT++, "Jim"));
        people.add(new Person(PEOPLE_COUNT++, "Tom"));
        people.add(new Person(PEOPLE_COUNT++, "Bob"));
        people.add(new Person(PEOPLE_COUNT++, "Mike"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.id() == id)
                .findFirst()
                .orElse(null);
    }

}
