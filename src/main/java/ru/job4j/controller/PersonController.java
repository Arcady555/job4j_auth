package ru.job4j.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.Person;
import ru.job4j.service.PersonService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonService persons;

    @GetMapping("/")
    public List<Person> findAll() {
        return this.persons.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@Valid @PathVariable int id) {
        var person = this.persons.findById(id);
        if (person.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person is not found. Please, check ID");
        }
        return new ResponseEntity<>(
                person.orElse(new Person()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public Person patch(@Valid @RequestBody Person person) throws InvocationTargetException, IllegalAccessException {
        var current = persons.findById(person.getId());
        if (current.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        HashMap<String, Method> namePerMethod = persons.methods(person);
        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Impossible invoke set method from object : " + current.get() + ", Check set and get pairs."
                    );
                }
                var newValue = getMethod.invoke(person);
                if (newValue != null) {
                    setMethod.invoke(current, newValue);
                }
            }
        }
        persons.save(person);
        return current.get();
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> create(@Valid @RequestBody Person person) {
        return this.persons.save(person) ? new ResponseEntity<>(
                this.persons.save(person),
                HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@Valid @RequestBody Person person) {
        return this.persons.save(person) ? ResponseEntity.ok().build()
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable int id) {
        Person person = new Person();
        person.setId(id);
        return this.persons.delete(person) ? ResponseEntity.ok().build()
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}