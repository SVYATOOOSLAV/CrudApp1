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
        people.add(new Person(PEOPLE_COUNT++, "Jim", 24, "jim@mail.ru"));
        people.add(new Person(PEOPLE_COUNT++, "Tom", 52, "tom@mail.ru"));
        people.add(new Person(PEOPLE_COUNT++, "Bob", 18, "bob@mail.ru"));
        people.add(new Person(PEOPLE_COUNT++, "Mike", 34, "mike@mail.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Person person) {
        person.setId(PEOPLE_COUNT++);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person newPerson = show(id);
        newPerson.setAge(person.getAge());
        newPerson.setName(person.getName());
        newPerson.setEmail(person.getEmail());
    }

    public void delete(int id){
        people.removeIf(person -> person.getId() == id);
    }
}
