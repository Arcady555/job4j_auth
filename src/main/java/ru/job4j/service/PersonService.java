package ru.job4j.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Person;
import ru.job4j.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Data
@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository persons;

    public List<Person> findAll() {
        return persons.findAll();
    }

    public Optional<Person> findById(int id) {
        return persons.findById(id);
    }

    public Person save(Person person) {
        return persons.save(person);
    }

    public void delete(Person person) {
        persons.delete(person);
    }
}