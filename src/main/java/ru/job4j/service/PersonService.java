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

    public boolean save(Person person) {
        var temp = findById(person.getId());
        temp.ifPresent(persons::save);
        return temp.isPresent();
    }

    public boolean delete(Person person) {
        var temp = findById(person.getId());
        temp.ifPresent(persons::delete);
        return temp.isPresent();
    }
}